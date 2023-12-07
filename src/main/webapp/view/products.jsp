<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../css/reset.css" />
    <link rel="stylesheet" href="../css/product.css" />
    <link rel="stylesheet" href="../css/cart.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
    />
    <link rel="stylesheet" href="../css/magnify.css" />

    <title>Product</title>
  </head>
  <body>
    <!-- header -->
    <header style="position: sticky">
      <div class="box">
        <div class="logo">
          <a href="" class="logo-box">
            <img src="./img/logo.jpg" alt="" />
          </a>
        </div>
        <div class="menu">
          <ul>
            <li><a href="toHome">HOME</a></li>
            <li><a href="loadProduct">PRODUCT</a></li>

          </ul>
        </div>
        <div class="search">
          <form action="searchProduct" class="search-box">
            <input
              type="text"
              class="search-text"
              placeholder="Search"
              name = "search"
            />
            <button class="search-btn">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </form>
        </div>
        <div class="shop">
          <a onclick="loadCart()">
            <i class="fa-solid fa-basket-shopping shop-icon">
              <span>  </span>
            </i>
          </a>

        </div>
        <div class="user">
          <a  class="user-box" onclick="onOfUseroptions()">
            <img src="./img/namngu.jpg" alt="" />
          </a>
          <div class="user-dropdown">
            <a href="./view/login.jsp">Log in</a>
            <a href="/loadProfile">Profile</a>
            <a href="logOut">Log out</a>
          </div>
        </div>
      </div>
    </header>
    <!-- End Header -->
    <!-- Box Container -->
    <div class="content">
      <div class="boxContainer">
        <div class="bodyContainer">
          <div class="productContainer">
            <div class="latest-product">
              <div class="search-message">${message}</div>
              <div class="product-container" id="product-container-id">

                <c:forEach items="${listProduct}" var="product">
                  <div class="product-item" id="product-item-id-${product.getProductId()}">
                    <div class="product-icon">
                      <a  class="view-icon" data-toggle="modal" data-target="#exampleModalCenter"
                      ><i class="fa-solid fa-eye"></i>
                      </a>
                    </div>
                    <div class="product-content">
                      <img
                              src="${product.getImage()}"
                              alt=""
                              class="product-img"
                      />
                      <h3 class="product-name">${product.getProductName()}</h3>
                      <div class="priceSaleBox">
                        <div class="priceSale"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.getPrice()}"/></div>
                      </div>
                    </div>
                  </div>

                </c:forEach>


              </div>
            </div>
          </div>
        </div>
        <!-- Cart -->
        <div class="cart">
          <form action="">
            <table>
              <thead class="titleTable">
                <tr>
                  <th class="imgProduct">Product</th>
                  <th class="nameProduct">Name</th>
                  <th class="priceProduct">Price</th>
                  <th class="amountsProduct">Amounts</th>
                  <th class="optionProduct">Option</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </form>
          <div class="totalPriceCart">
            <p>Total: <span>0</span> <sub>VND</sub></p>
          </div>
        </div>
        <!-- End Cart -->
        <!-- Modals -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
              <!-- <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Detail</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div> -->
              <div class="modal-body">
                <!-- <div class="newProduct">
                
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                
                  <div class="newProductContainer">
                    <div class="newProductItem">
                      <div class="newProductBox">
                        <div class="productImg">
                          <div class="productImgContent">
                            <img src="./img/productred/1.jpg" alt="" class="bigImg1" />
                          </div>
                        </div>
                        <div class="productContent">
                          <h3 class="productName">NEW NIKE AIRMAC SHOES</h3>
                          <div class="sizeBox">
                            <p>Size</p>
                            <div class="sizeBoxoption">
                              <div
                                class="sizeOption size37 active"
                                onclick="changeSize(37)"
                              >
                                37
                              </div>
                              <div class="sizeOption size38" onclick="changeSize(38)">
                                38
                              </div>
                              <div class="sizeOption size39" onclick="changeSize(39)">
                                39
                              </div>
                              <div class="sizeOption size40" onclick="changeSize(40)">
                                40
                              </div>
                              <div class="sizeOption size41" onclick="changeSize(41)">
                                41
                              </div>
                            </div>
                          </div>
                          <p>
                            Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                            Dignissimos, repellendus magni alias, quibusdam rerum placeat
                            sit ipsa velit dolorum nostrum hic laudantium commodi at quod
                            voluptatem illum quae! Deserunt, optio!
                          </p>
                          <div class="price">
                            <div class="price">200.000VND <span>150.000VND</span></div>
                            <button class="cartBtn">ADD TO CART</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div> -->
              </div>
              <!-- <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
              </div> -->
            </div>
          </div>
        </div>
        <!-- End Modals -->
      </div>
    </div>
    <!-- End BoxContainer -->
    <!-- Footer -->
    <div class="footer-container">
      <div class="footer">
        <div class="footer-heading footer-1">
          <h2>GET HELP</h2>
          <h2>BECOME A MEMBER</h2>
          <h2>SIGN UP FOR EMAIL</h2>
          <h2>SEND US FEEDBACK</h2>
        </div>
        <div class="footer-heading footer-2">
          <h2>GET HELP</h2>
          <a href="#">Order Status</a>
          <a href="#">Delivery</a>
          <a href="#">Returns</a>
          <a href="#">Payment Options</a>
          <a href="#">Contact Us</a>
        </div>
        <div class="footer-heading footer-3">
          <h2>ABOUT US</h2>
          <a href="#">News</a>
          <a href="#">Careers</a>
          <a href="#">Investors</a>
          <a href="#">Sustainability</a>
        </div>
        <div class="footer-email-form">
          <h2>Join our newsletter</h2>
          <input
            type="email"
            placeholder="Enter your email address"
            id="footer-email"
          />
          <input type="submit" value="Sign up" id="footer-email-btn" />
        </div>
        <div class="footer-payment-container">
          <h2>PAYMENT</h2>
          <img width="250px" src="./img/footer/payment.jpg" alt="" />
        </div>
      </div>
      <div class="copyright">
        <div class="copyright-container">
          <p>2023 GreatGreen. All Rights Reserved.</p>
        </div>
      </div>
    </div>
    <!-- End footer -->
    <script src="./js/products.js"></script>
    <script src="./js/cart.js"></script>
    <!-- <script src="./js/main.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </body>
</html>
