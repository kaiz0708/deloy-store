
editProduct();
function editProduct() {
  var cart = document.querySelector("tbody");
  var listProduct = cart.querySelectorAll("tr");
  listProduct.forEach((item) => {
    var editbtn = item.querySelector(".btn-edit");
    editbtn.addEventListener("click", (e) => {
      let productId = item.getAttribute("id");
      productId = deleteLetter(productId, "product-id-");
      var productName = item.querySelector(".productName").textContent;
      var price = item.querySelector(".productPrice").textContent;
      var brand = item.querySelector(".brandProduct").textContent;
      var gender = item.querySelector(".gender").textContent;
      var status = item.querySelector(".status").textContent;

      var image = item.querySelector(".circle-image").src;
      var category = item.querySelector(".categoryProduct").textContent;
      var color = item.querySelector(".color").textContent;

      // Gán giá trị vào các trường trong form trong modal
      document.querySelector("#editModal #inputProductId").value = productId;
      document.querySelector("#editModal #inputProductName").value =
        productName;
      document.querySelector("#editModal #inputPrice").value = price;
      document.querySelector("#editModal #brandId").value = brand;
      document.querySelector("#editModal #genderId").value = gender
      document.querySelector("#editModal #categoryId").value = category;
      document.querySelector("#editModal #inputColor").value = color;
      document.querySelector("#editModal #statusId").value = status;
      document.querySelector("#editModal #img1").src = image;
    });
  });
}
deleteProduct();
function deleteProduct() {

  var cart = document.querySelector("tbody");
  var listProduct = cart.querySelectorAll("tr");
  listProduct.forEach((item) => {

    var deletebtn = item.querySelector(".btn-delete");

    deletebtn.addEventListener("click", () => {
      var confirmbutton = document.querySelector(".confirmdelete");
      console.log(confirmbutton);

      confirmbutton.addEventListener("click", () => {
        let productId = deleteLetter(item.getAttribute("id"), "product-id-");
        console.log(productId);
        deleteShoe(productId);
      });

    });
  });
}

// addProductAdd()
// function addProductAdd() {
//
//   document.querySelector(".addProduct")
//       .addEventListener("click", () =>  {
//         // ngăn form gửi dữ liệu theo cách thông thường
//         // Lấy giá trị các trường form
//
//         var productName = document.getElementById("inputProductName").value;
//         var color = document.getElementById("inputColor").value;
//         var price = document.getElementById("inputPrice").value;
//         var sex = document.querySelector('input[name="gridRadios"]:checked').value;
//         var img1 = document.getElementById("img1").value;
//         var brand = document.getElementById("brandId").value;
//         var category = document.getElementById("categoryId").value;
//         var description = document.getElementById("inputDescription").value;
//
//         addProductToStore(
//             productName,
//             color,
//             price,
//             sex,
//             img1,
//             brand,
//             category,
//             description
//         );
//         // editProduct();
//         // deleteProduct();
//         // Reset form
//         // document.getElementById("formModal").reset();
//       });
// }


function addProductToStore(
  name,
  color,
  price,
  sex,
  img1,
  brand,
  category,
  description
) {
  var cart = document.querySelector("tbody");
  var listProduct = cart.querySelectorAll("tr");
  var productItem = document.createElement("tr");
  var productContent = `<tr>
                    <td class="productName">${name}</td>
                    <td class="productPrice">${price} Vnd</td>
                    <td class="brandProduct">${brand}</td>
                    <td class="gender">${sex}</td>
                    <td class="categoryProduct">${category}</td>
                    <td class="color">${color}</td>
                    <td><span class="status delivered">Delivered</span></td>
                    <td>
                      <img
                        class="circle-image"
                        src="${img1}"
                        alt="Hình ảnh"
                      />
                    </td>
                    <td class="description">${description}</td>
                    <td class="tdButton">
                      <div class="cardBtn">
                        <a href="#" class="btn-edit" data-toggle="modal"
                        data-target="#editModal">Edit</a>
                      </div>
                      <div class="cardBtn">
                        <a href="#" class="btn-delete" data-toggle="modal"
                        data-target="#modalConfirm">Delete</a>
                      </div>
                    </td>
                  </tr>`;
  productItem.innerHTML = productContent;
  addProduct(name,
      color,
      price,
      sex,
      img1,
      brand,
      category,
      description)
}

function deleteLetter(str, letter) {
  return str.replace(new RegExp(letter, "g"), "");
}

// function addProduct(name,
//                     color,
//                     price,
//                     sex,
//                     img1,
//                     brand,
//                     category,
//                     description) {
//
//   let ob = {
//         name : name,
//         color : color,
//         price : price,
//         sex : sex,
//         img1 : img1,
//         brand : brand,
//         category : category,
//         description : description
//   }
//   let data = JSON.parse(JSON.stringify(ob));
//   $.ajax({
//     url: "/addProduct",
//     type: "post",
//     dataType: "json",
//     data: data,
//     success: function () {
//      alert("Add product success");
//     },
//     error: function (xhr) {
//       console.log("Error: " + xhr.responseText);
//     }
//   });
// }

function deleteShoe(productId) {
  $.ajax({
    url: "/deleteProduct",
    type: "get",
    data: {
      productId: productId,
    },
    success: function () {
      loadAllProduct();
    },
    error: function (xhr) {
      console.log("Error: " + xhr.responseText);
    }
  });
}

function loadAllProduct(){
  $.ajax({
    url: "/loadProduct",
    type: "post",
    dataType: "json",
    success: function (data) {
      var productList = JSON.parse(JSON.stringify(data));
      let tBodyProduct = document.getElementById("tbody-product");
      tBodyProduct.innerHTML = ``;

      productList.forEach((product) => {
        tBodyProduct.innerHTML += `
                    <tr id="product-id-${product.productId}">
                      <td>${product.productName}</td>
                      <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.price}"/></td>
                      <td>${product.brand}</td>
                      <td>${product.gender}</td>
                      <td>${product.category}</td>
                      <td>${product.color}</td>
                      <td>${product.productStatus}</td>
                      <td>
                        <img class="circle-image" src="${product.image}" alt="${product.productName} Image">
                      </td>
                      <td>
                        <div class="cardBtn">
                          <button class="btn-edit" data-toggle="modal" data-target="#editModal">Edit</button>
                        </div>
                        <div class="cardBtn">
                          <button id="delete-btn-id-${product.productId}" class="btn-delete" data-toggle="modal" data-target="#modalConfirm">Delete</button>
                        </div>
                      </td>
                    </tr>`
      })

    },
    error: function (xhr) {
      console.log("Error: " + xhr.responseText);
    }
  });
}
