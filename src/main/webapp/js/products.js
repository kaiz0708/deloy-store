const sortDropDownBtn = document.querySelector(".Sort-btn");
const sortDropDown = document.querySelector(".sortDropDown");
const hideFilterbtn = document.querySelector(".filter-btn");
const filterBox = document.querySelector(".filterContainer");
function activeDropDown() {
  sortDropDown.classList.toggle("active");
}
//show-off filter-box-item
function toggleFilterBoxItem(e) {
  var item = document.querySelector("." + e + " .boxContent");
  item.classList.toggle("hidden");
  // console.log(item);
}
//show-off filter-box
function toggleFilterBox() {
  filterBox.classList.toggle("hidden");
}

document.getElementById("sortPriceIn").addEventListener("click", sortPriceIn);
function sortPriceIn() {
    console.log("work")
    $.ajax({
        url: "/sortPriceIn",
        type: "get",
        success: function(data) {
            var jsonData = JSON.parse(JSON.stringify(data));
            console.log(jsonData);

            var productContainter = document.getElementById("product-container-id");
            productContainter.innerHTML = ``;
            var productHTML = ``;

            jsonData.forEach((product) => {
                var formatter = new Intl.NumberFormat(undefined, {
                    maximumFractionDigits: 3
                });
                var formattedPrice = formatter.format(product.price);
                productContainter.innerHTML += `
                    <div class="product-item" id="product-item-id-${product.productId}">
                        <div class="product-icon">
                          <a  class="view-icon" data-toggle="modal" data-target="#exampleModalCenter"
                          ><i class="fa-solid fa-eye"></i>
                          </a>
                        </div>
                        <div class="product-content">
                          <img
                                  src="${product.images}"
                                  alt=""
                                  class="product-img"
                          />
                          <h3 class="product-name">${product.productName}</h3>
                          <div class="priceSaleBox">
                            <div class="priceSale">${formattedPrice}</div>
                          </div>
                        </div>
                  </div>
            `;
                addModalBtn=document.querySelectorAll(".view-icon");
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
            })
        },
        error: function(xhr){
            console.log("Error:" + xhr.responseText);
        }
    });
}

document.getElementById("sortPriceDe").addEventListener("click", sortPriceDe);
function sortPriceDe() {
    console.log("work")
    $.ajax({
        url: "/sortPriceDe",
        type: "get",
        success: function(data) {
            var jsonData = JSON.parse(JSON.stringify(data));
            console.log(jsonData);

            var productContainter = document.getElementById("product-container-id");
            productContainter.innerHTML = ``;
            var productHTML = ``;

            jsonData.forEach((product) => {
                var formatter = new Intl.NumberFormat(undefined, {
                    maximumFractionDigits: 3
                });
                var formattedPrice = formatter.format(product.price);

                productContainter.innerHTML += `
                    <div class="product-item" id="product-item-id-${product.productId}">
                    <div class="product-icon">
                      <a  class="view-icon" data-toggle="modal" data-target="#exampleModalCenter"
                      ><i class="fa-solid fa-eye"></i>
                      </a>
                    </div>
                    <div class="product-content">
                      <img
                              src="${product.images}"
                              alt=""
                              class="product-img"
                      />
                      <h3 class="product-name">${product.productName}</h3>
                      <div class="priceSaleBox">
                        <div class="priceSale">${formattedPrice}</div>
                      </div>
                    </div>
                  </div>
            `;
                addModalBtn=document.querySelectorAll(".view-icon");
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
            })
        },
        error: function(xhr){
            console.log("Error:" + xhr.responseText);
        }
    });
}


