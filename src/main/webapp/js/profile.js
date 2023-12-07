// chuyen Tab
const profileOption=document.querySelector(".profileOption")
const cartOption=document.querySelector(".cartOption");
const profileTab=document.querySelector(".profileTab")
const cartTab=document.querySelector(".cartTab");
const invoiceTab = document.querySelector(".invoiceTab");
function activeProfileTab(){  
    profileTab.classList.add("active")
    cartTab.classList.remove("active")
    invoiceTab.classList.remove("active")
}
function activeCartTab(){
    profileTab.classList.remove("active")
    cartTab.classList.add("active")
    invoiceTab.classList.remove("active")
    loadCart();
}
function activeInvoiceTab(){
    profileTab.classList.remove("active")
    cartTab.classList.remove("active")
    invoiceTab.classList.add("active")
    loadInvoice();
}

const mainTotalPrice = document.querySelector(".totalPriceCart span");
const cart = document.querySelector("tbody");
function loadCart() {
    $.ajax({
        url: "/loadCart",
        type: "get",
        success: function(data) {
            var jsonData = JSON.parse(JSON.stringify(data));

            jsonData = jsonData[0];
            console.log(jsonData);

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
function deleteLetter(str, letter) {
    return str.replace(new RegExp(letter, "g"), "");
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

function loadInvoice() {

    let table = document.querySelector("#tbody-invoice");
    table.innerHTML = ``;
    $.ajax({
        url: "/loadInvoice",
        type: "get",
        dataType: "json",
        success: function(data) {
            var jsonData = JSON.parse(JSON.stringify(data));

            console.log(jsonData)

            jsonData.forEach((invoice) => {

                console.log(invoice)

                let listItem = invoice["listItems"];
                console.log(listItem);
                listItem.forEach((item) =>{
                    var formatter = new Intl.NumberFormat(undefined, {
                        maximumFractionDigits: 3
                    });
                    var price = item["product"].price * item.amount;
                    var formattedPrice = formatter.format(price);
                    let productItem = document.createElement("tr");

                    var productContent = ` 
            <tr class="cartItems" id="cartItems-id-${item.productId}">
               <td class="imgItems">
                    <div class="">
                        ${invoice.invoiceId}
                    </div>
                </td>
                <td class="imgItems">
                    <div class="">
                        <img src=${item["product"].image} alt="" />
                    </div>
                </td>
                <td class="nameItems">
                    <div class="">
                        <p>${item["product"].productName}</p>
                    </div>
                </td>
                <td class="priceItems">
                    <div class="">${formattedPrice} VND</div>
                </td>
                <td class="amountsItems">
                    <div class="">
                        ${item.amount}
                    </div>
                </td>
                <td class="deliItems">
                    <div class="">
                        ${invoice.deliveryAddress}
                    </div>
                </td>
            </tr>`;
                    productItem.innerHTML = productContent;
                    table.append(productItem);

                })


            });
        },
        error: function(xhr){
            console.log("Error:" + xhr.responseText);
        }
    });
}
var listCartProduct = document.querySelectorAll("#tbody-cart");
var buttonCalculate = document.querySelector(".buttonCalculate");
if (listCartProduct.length > 0) {
    buttonCalculate.classList.add("active");
} else {
    buttonCalculate.classList.remove("active");
}
function checkOut(productId, amount){
    $.ajax({
        url: "/checkOut",
        type: "post",
        data: {
            productId : productId,
            amount : amount
        },
        success: function() {
            alert("Payment success")
        },
        error: function(xhr){
            console.log("Error:" + xhr.responseText);
        }
    });
}
