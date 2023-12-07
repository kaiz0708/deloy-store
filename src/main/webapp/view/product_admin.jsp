<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    /> -->
    <title>Admin Page</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="./css/style_admin.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link rel="stylesheet" href="./css/productadmin.css" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />
  </head>

  <body>
    <!-- =============== Navigation ================ -->
    <div class="navigationContainer">
      <div class="navigation">
        <ul>
          <li>
            <a href="toHome">
              <span class="icon">
                <ion-icon name="logo-apple"></ion-icon>
              </span>
              <span class="title title-name" style="font-size: 18px"
                >GreatGreen</span
              >
            </a>
          </li>

          <li class="dashboard-menu-items hovered" id="product-hover">
            <a href="./product.html">
              <span class="icon">
                <ion-icon name="people-outline"></ion-icon>
              </span>
              <span class="title">Product</span>
            </a>
          </li>
        </ul>
      </div>

      <div class="tab-indicator">
        <!-- ========================= Main ==================== -->
        <div class="main content toggle-active">
          <!-- ======================= Cards ================== -->

          <!-- ================ Order Details List ================= -->
          <div class="details">
            <div class="recentOrders">
              <div class="cardHeader">
                <h2>Product</h2>
                <button
                  type="button"
                  class="btn btn-primary addModal"
                  data-toggle="modal"
                  data-target="#exampleModalCenter"
                >
                  Add
                </button>
              </div>
              <div class="mt-15">
                <form action="searchProduct" method="post" >
                  <input
                          style="float: right"
                          type="search"
                          class="form-control form-control-sm"
                          placeholder=""
                          aria-controls="dataTable"
                          name="search"
                  />
                  <span
                          style="float: right; margin-top: 5px; font-family: sans-serif"
                  >Search</span>
                </form>


              </div>
              <table>
                <thead>
                  <tr>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Brand</td>
                    <td>State</td>
                    <td>Category</td>
                    <td>Status</td>
                    <td>Image</td>
                    <td>Description</td>
                  </tr>
                </thead>

                <tbody id="tbody-product">
                  <c:forEach items="${productList}" var="product">
                    <tr id="product-id-${product.getProductId()}">
                      <td class="productName">${product.getProductName()}</td>
                      <td class="productPrice"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.getPrice()}"/></td>
                      <td class="brandProduct">${product.getBrand().getBrandName()}</td>
                      <td class="gender">${product.getGender()}</td>
                      <td class="categoryProduct">${product.getCategory().getCatName()}</td>
                      <td class="status">${product.getProductStatus()}</td>
                      <td>
                        <img class="circle-image" src="${product.getImage()}" alt="${product.getProductName()} Image">
                      </td>
                      <td>
                        ${product.getDescription()}
                      </td>
                      <td>
                        <div class="cardBtn">
                          <button class="btn-edit" data-toggle="modal" data-target="#editModal">Edit</button>
                        </div>
                        <div class="cardBtn">
                          <button id="delete-btn-id-${product.getProductId()}" class="btn-delete" data-toggle="modal" data-target="#modalConfirm">Delete</button>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModalCenter"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add Product</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="formModal" action="addProduct" enctype='multipart/form-data' method="post">
              <div class="form-group row">
                <label for="inputProductName" class="col-sm-2 col-form-label"
                  >Product Name</label
                >
                <div class="col-sm-10">
                  <input
                    type="text"
                    class="form-control"
<%--                    id="inputProductName"--%>
                    placeholder="ProductName"
                    name="productName"
                  />
                </div>
              </div>
<%--              <div class="form-group row">--%>
<%--                <label for="inputColor" class="col-sm-2 col-form-label"--%>
<%--                  >Color</label--%>
<%--                >--%>
<%--                <div class="col-sm-10">--%>
<%--                  <input--%>
<%--                    type="text"--%>
<%--                    class="form-control"--%>
<%--&lt;%&ndash;                    id="inputColor"&ndash;%&gt;--%>
<%--                    placeholder="Color"--%>
<%--                    name="color"--%>
<%--                  />--%>
<%--                </div>--%>
              <div class="form-group row">
                <label for="inputPrice" class="col-sm-2 col-form-label"
                  >Price</label
                >
                <div class="col-sm-10">
                  <input
                    type="text"
                    class="form-control"
<%--                    id="inputPrice"--%>
                    placeholder="Price"
                    name="price"
                  />
                </div>
              </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"
                    >Quantity</label
                    >
                    <div class="col-sm-10">
                        <input
                                type="text"
                                class="form-control"
                        <%--                    id="inputPrice"--%>
                                placeholder="Quantity"
                                name="quantity"
                        />
                    </div>
                </div>
              <fieldset class="form-group">
                <div class="row">
                  <legend class="col-form-label col-sm-2 pt-0">State</legend>
                  <div class="col-sm-10">
                    <select class="form-control"
<%--                            id="genderId" --%>
                            name="gender">
                      <option>drying</option>
                      <option>fresh</option>
                    </select>
                  </div>
                </div>
              </fieldset>
              <div class="form-group row">
                <label for="img1" class="col-sm-2 col-form-label">Image</label>
                <div class="col-sm-10">
                  <input type="file" class="form-control-file"
<%--                         id="img1"--%>
                         name="image" accept="image/png, image/gif, image/jpeg"/>
                </div>
              </div>
              <div class="form-group row">
                <%--@declare id="brandid "--%><label for="brandId " class="col-sm-2 col-form-label"
                  >Supplier Name</label
                >
                <div class="col-sm-10">
                  <select class="form-control"
<%--                          id="brandId"--%>
                          name="brandName">
                    <option>VinaFood</option>
                    <option>Intimex</option>
                    <option>Vilaconic</option>
                  </select>
                </div>
              </div>
                <div class="form-group row">
                  <label for="inputPrice" class="col-sm-2 col-form-label"
                  >Description</label
                  >
                  <div class="col-sm-10">
                    <input
                            type="text"
                            class="form-control"
<%--                            id="inputDescription"--%>
                            placeholder="Description"
                            name="description"
                    />
                  </div>
                </div>
              <div class="form-group row">
                <%--@declare id="categoryid "--%><label for="categoryId " class="col-sm-2 col-form-label"
                  >Category</label
                >
                <div class="col-sm-10">
                  <select class="form-control"
<%--                          id="categoryId"--%>
                          name="catName">
                    <option>Bean</option>
                    <option>Tubers</option>
                    <option>Vegetable</option>
                  </select>
                </div>
              </div>

              <div class="form-group row">
                <label for="categoryId " class="col-sm-2 col-form-label"
                >Status</label
                >
                <div class="col-sm-10">
                  <select class="form-control"
<%--                          id="statusId"--%>
                          name="status">
                    <option>Active</option>
                    <option>Inactive</option>
                  </select>
                </div>
              </div>

              <div class="form-group row">
                <div
                  class=""
                  style="width: 100%; justify-content: center; display: flex"
                >
                  <button
                    type="submit"
                    class="btn btn-primary addProduct"
                    style="width: 70%"
                  >
                    Add
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal confirm -->
    <div
      class="modal fade"
      id="modalConfirm"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title"
<%--                id="exampleModalLongTitle"--%>
            >
              Delete product
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">Are you sure????</div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-primary confirmdelete"
              data-dismiss="modal"
            >
              Yes
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal Edit -->
    <div
      class="modal fade"
      id="editModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="eidtModal"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLongTitle">Edit Product</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="formModal" action="editProduct" method="post" enctype='multipart/form-data'>
              <div class="form-group row" hidden="hidden">
                <label for="inputProductId" class="col-sm-2 col-form-label"
                >Product Id</label
                >
                <div class="col-sm-10">
                  <input
                          type="text"
                          class="form-control"
                          id="inputProductId"
                          placeholder="ProductId"
                          name="editProductId"
                  />
                </div>
              </div>

              <div class="form-group row">
                <label for="inputProductName" class="col-sm-2 col-form-label"
                  >Product Name</label
                >
                <div class="col-sm-10">
                  <input
                    type="text"
                    class="form-control"
                    id="inputProductName"
                    name = "editProductName"
                    placeholder="ProductName"
                    required
                  />
                </div>
              </div>
<%--              <div class="form-group row">--%>
<%--                <label for="inputColor" class="col-sm-2 col-form-label"--%>
<%--                  >Color</label--%>
<%--                >--%>
<%--                <div class="col-sm-10">--%>
<%--                  <input--%>
<%--                    type="text"--%>
<%--                    class="form-control"--%>
<%--                    id="inputColor"--%>
<%--                    placeholder="Color"--%>
<%--                    name="editColor"--%>
<%--                    required--%>
<%--                  />--%>
<%--                </div>--%>

              <div class="form-group row">
                <label for="inputPrice" class="col-sm-2 col-form-label"
                  >Price</label
                >
                <div class="col-sm-10">
                  <input
                    type="text"
                    class="form-control"
                    id="inputPrice"
                    placeholder="Price"
                    name="editPrice"
                    required
                  />
                </div>
              </div>
                <div class="form-group row">
                    <label for="inputPrice" class="col-sm-2 col-form-label"
                    >Description</label
                    >
                    <div class="col-sm-10">
                        <input
                                type="text"
                                class="form-control"
                                                    id="inputDescription"
                                placeholder="Description"
                                name="editDescription"
                        />
                    </div>
                </div>
              <fieldset class="form-group">
                <div class="row">
                  <legend class="col-form-label col-sm-2 pt-0">State</legend>
                  <div class="col-sm-10">
                    <select class="form-control" id="genderId" name="editGender">
                      <option>drying</option>
                      <option>fresh</option>
                    </select>
                  </div>
                </div>
              </fieldset>
              <div class="form-group row">
                <label for="img1" class="col-sm-2 col-form-label">Image</label>
                <div class="col-sm-10">
                  <input type="file" class="form-control-file" id="img1" name="editImage" />
                </div>
              </div>
              <div class="form-group row">
                <label for="brandId " class="col-sm-2 col-form-label"
                  >Supplier Name</label
                >
                <div class="col-sm-10">
                  <select class="form-control" id="brandId" name="editBrand">
                      <option>VinaFood</option>
                      <option>Intimex</option>
                      <option>Vilaconic</option>

                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label for="categoryId " class="col-sm-2 col-form-label"
                  >Category</label
                >
                <div class="col-sm-10">
                  <select class="form-control" id="categoryId" name="editCategory">
                      <option>Bean</option>
                      <option>Tubers</option>
                      <option>Vegetable</option>
                  </select>
                </div>
              </div>

              <div class="form-group row">
                <label for="categoryId " class="col-sm-2 col-form-label"
                >Status</label
                >
                <div class="col-sm-10">
                  <select class="form-control" id="statusId" name="editStatus">
                    <option>Active</option>
                    <option>Inactive</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <div
                  class=""
                  style="width: 100%; justify-content: center; display: flex"
                >
                  <button
                    type="submit"
                    class="btn btn-primary editProduct"
                    style="width: 70%"
                  >
                    Edit
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- =========== Scripts =========  -->
    <script src="../js/main.js"></script>
    <script src="../js/productadmin.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <!-- ====== ionicons ======= -->
    <script
      type="module"
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
    ></script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
    ></script>
    <!-- Bootstrap -->
<%--    <script--%>
<%--      src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--      integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--      crossorigin="anonymous"--%>
<%--    ></script>--%>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
