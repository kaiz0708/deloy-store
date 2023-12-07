const addcartBtn = document.querySelectorAll(".cart-icon");
const mainTotalPrice = document.querySelector(".totalPriceCart span");
const cart = document.querySelector("tbody");
const tabCart = document.querySelector(".cart");
const toggleCart = document.querySelector(".shop-icon");
const bodyModal= document.querySelector(".modal-body");

var addModalBtn=document.querySelectorAll(".view-icon");

toggleCart.addEventListener("click", () => {
  tabCart.classList.toggle("active");
});

function deleteLetter(str, letter) {
  return str.replace(new RegExp(letter, "g"), "");
}

addModalBtn.forEach((item)=>{
    item.addEventListener("click",(e)=>{  
    var product = e.target.closest(".product-item");
    var imgProduct = product.querySelector(".product-img").src;
    var nameProduct = product.querySelector(".product-name").innerText;
    var priceSale = product.querySelector(".priceSale").innerText;
    var productId = product.getAttribute("id");
    productId = deleteLetter(productId, "product-item-id-");
    console.log(productId);

    addToModal(imgProduct, nameProduct, priceSale, productId);
    });
});
function addToModal(img, name, priceSale, productId){
  $.ajax({
    url: "/loadProductDetail",
    type: "get",
    data: {
      productId : productId,
    },
    success: function(data) {
      var product = JSON.parse(JSON.stringify(data));
      product = product[0];
      console.log(product);

      var contentModal=`  <div class="newProduct">
                  <div class="newProductContainer">
                    <div class="newProductItem">
                      <div class="newProductBox">
                        <div class="productImg">
                          <div class="productImgContent">
                            <img src=${img} alt="" class="bigImg1" />
                          </div>
                        </div>
                        <div class="productContent">
                          <h3 class="productName">${name}</h3>
                          <h4 class="productName">Brand: ${product.brand}</h4>
                          <h4 class="productName">Category: ${product.category}</h4>
                          <p>
                            <b>Description :</b>
                            ${product.description}
                          </p>
                          <div class="price">
                            <div class="price">${priceSale}</div>
                            <button class="cartBtn" onclick="addToCart(${productId})">ADD TO CART</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  <div class="result" >
                      
                   </div>
                </div>`

      bodyModal.innerHTML=contentModal;
      // renewAddToCart(img,name,priceSale);
      // changeSize()
    },
    error: function(xhr){
      console.log("Error:" + xhr.responseText);
    }
  });
          
}
//chức năng đổi size trong Modal
function changeSize() {
  var listSizeOption = document.querySelectorAll(
    ".newProduct .sizeBoxoption .sizeOption"
  );
  listSizeOption.forEach((item)=>{
      item.addEventListener("click",(e)=>{
        listSizeOption.forEach((item)=>{
          item.classList.remove("active")
        })
        e.target.classList.toggle("active");
      })
    })  
}
function renewAddToCart(img, name, price){
  changeSize();
  var addbtn=document.querySelector(".cartBtn")
  addbtn.addEventListener("click",()=>{
    var check = 0;
  var listProduct = cart.querySelectorAll("tr");
  var productItem = document.createElement("tr");
  var size=document.querySelector(".sizeOption.active ").innerText;
  var productContent = ` 
    <tr class="cartItems">
        <td class="imgItems">
            <div class="">
                <img src=${img} alt="" />
            </div>
        </td>
        <td class="nameItems">
            <div class="">
                <p>${name}</p>
            </div>
        </td>
        <td class="priceItems">
            <div class="">${price}</div>
        </td>
        <td class="sizeItems">
            <div class="">${size}</div>
        </td>
        <td class="amountsItems">
            <div class="">
                <input type="number" value="1" min="1" />
            </div>
        </td>
        <td class="deleteProductOption">
            <div class="boxDeleteBtn">
                <div class="deleteBtn" >Delete all</div>
            </div>
        </td>
    </tr>`;
  productItem.innerHTML = productContent;
  if (listProduct.length > 0) {
    listProduct.forEach((item) => {
      var itemName = item.querySelector(".nameItems p").innerText;

      var itemimg = item.querySelector("img").src;
      var itemsize= item.querySelector(".sizeItems div").innerText;
      var amount = item.querySelector("input").value;
      if (img == itemimg && name == itemName && size==itemsize ) {
        item.querySelector("input").value = parseInt(amount) + 1;
        check = 1;
      }
    });
  } else {
    cart.append(productItem);
  }
  if (check == 0) {
    cart.append(productItem);
  }
  // var amountCartHeader=document.querySelector(".shop span")
  // amountCartHeader.innerText=listProduct.length;
  deleteCart();
  calculateTotalPrice();
  inputChange();
  })
}
function deleteCart() {
  var listProduct = cart.querySelectorAll("tr");
  listProduct.forEach((item) => {
    var deleteBtn = item.querySelector(".deleteBtn");
    deleteBtn.addEventListener("click", () => {
      item.remove();
      calculateTotalPrice();
    });
  });
}

// chức năng open/close useroptions
function onOfUseroptions() {
  let useritem = document.querySelector(".user-dropdown");
  useritem.classList.toggle("active");
}


function addToCart(productId) {

  $.ajax({
    url: "/addToCart",
    type: "get",
    data: {
      productId: productId
    },
    success: function(results) {
      console.log(results);
      alert("Thêm vào giỏ hàng thành công")
    },
    error: function(xhr){
      console.log("Error:" + xhr.responseText);
    }
  });
}

function loadCart() {
  $.ajax({
    url: "/loadCart",
    type: "get",
    success: function(data) {
      var jsonData = JSON.parse(JSON.stringify(data));

      jsonData = jsonData[0];

      var price = parseInt(jsonData.totalPrice);
      //console.log(jsonData.totalPrice);

      mainTotalPrice.innerHTML = price.toLocaleString("de-DE");

      items = jsonData.cartItems;
      //console.log(items);

      cart.innerHTML = ``;

      items.forEach((item) => {
        var productItem = document.createElement("tr");
        productItem.setAttribute("id", "cartItems-id-" + item.productId);
        var productContent = ` 
            <tr class="cartItems" id="cartItems-id-${item.productId}">
                <td class="imgItems">
                    <div class="">
                        <img src=${item.productImage} alt="" />
                    </div>
                </td>
                <td class="nameItems">
                    <div class="">
                        <p>${item.productName}</p>
                    </div>
                </td>
                <td class="priceItems">
                    <div class="">${item.productPrice}</div>
                </td>
                <td class="amountsItems">
                    <div class="">
                        <input type="button" id="amountItem-id-${item.productId}" value="-" onclick="decreaseAmount(this)">
                        <input id="input-amountItem-id-${item.productId}" type="text" 
                        value="${item.amount}" min="1" onchange="updateCart(productId, amount)">
                        <input type="button" id="amountItem-id-${item.productId}" value="+" onclick="increaseAmount(this)">  
                    </div>
                </td>
                <td class="deleteProductOption">
                    <div class="deleteBtn" id="deleteBtn-id-${item.productId}" onclick="deleteItem(this)">Delete all</div>
                </td>
            </tr>`;
        productItem.innerHTML = productContent;
        cart.append(productItem);
      })

    },
    error: function(xhr){
      console.log("Error:" + xhr.responseText);
    }
  });
}

function updateCart(productId, amount) {

  console.log(productId);

  console.log(amount);

  productId = deleteLetter(productId, "input-amountItem-id-");

  console.log("work");
  $.ajax({
    url: "/updateCart",
    type: "get",
    data: {
      productId: productId,
      productAmount: amount,
    },
    success: function(data) {

      var jsonData = JSON.parse(JSON.stringify(data));
      jsonData = jsonData[0];

      var price = parseInt(jsonData.totalPrice);
      //console.log(jsonData.totalPrice);

      mainTotalPrice.innerHTML = price.toLocaleString("de-DE");

      items = jsonData.cartItems;
      //console.log(items);

      cart.innerHTML = ``;

      items.forEach((item) => {
        var productItem = document.createElement("tr");
        productItem.setAttribute("id", "cartItems-id-" + item.productId);
        var productContent = ` 
            <tr class="cartItems" id="cartItems-id-${item.productId}">
                <td class="imgItems">
                    <div class="">
                        <img src=${item.productImage} alt="" />
                    </div>
                </td>
                <td class="nameItems">
                    <div class="">
                        <p>${item.productName}</p>
                    </div>
                </td>
                <td class="priceItems">
                    <div class="">${item.productPrice}</div>
                </td>
                <td class="amountsItems">
                    <div class="">
                        <input type="button" id="amountItem-id-${item.productId}" value="-" onclick="decreaseAmount(this)">
                        <input id="input-amountItem-id-${item.productId}" type="text" 
                        value="${item.amount}" min="1" onchange="updateCart(productId, amount)">
                        <input type="button" id="amountItem-id-${item.productId}" value="+" onclick="increaseAmount(this)">              

                    </div>
                </td>
                <td class="deleteProductOption">
                    
                    <div class="deleteBtn" id="deleteBtn-id-${item.productId}" onclick="deleteItem(this)">Delete all</div>
               
                    <div class="calBtn" id="calBtn-id-${item.productId}" onclick="checkOut(${item.productId}, ${item.amount})">Canculer all</div> 
                </td>
            </tr>`;
        productItem.innerHTML = productContent;
        cart.append(productItem);
      })
    },
    error: function(xhr){
      console.log("Error:" + xhr.responseText);
    }
  });
}

function increaseAmount(element) {
  var currentInput = element.getAttribute("id");
  currentInput = "input-" + currentInput;
  var currentAmount = parseInt(document.getElementById(currentInput).value);

  currentAmount++;
  console.log(currentAmount);
  document.getElementById(currentInput).value = currentAmount;

  updateCart(currentInput, currentAmount);
}

function decreaseAmount(element) {
  var currentInput = element.getAttribute("id");
  currentInput = "input-" + currentInput;
  var currentAmount = parseInt(document.getElementById(currentInput).value);

  if (currentAmount !=0) {
    currentAmount--;
  }
  console.log(currentAmount);
  document.getElementById(currentInput).value = currentAmount;

  updateCart(currentInput, currentAmount);
}
function deleteItem(element) {
  let productId = element.getAttribute("id");
  productId = deleteLetter(productId, "deleteBtn-id-");

  $.ajax({
    url: "/deleteCartItem",
    type: "get",
    data: {
      productId: productId
    },
    success: function() {
      loadCart();
    },
    error: function(xhr){
      console.log("Error:" + xhr.responseText);
    }
  });
}
