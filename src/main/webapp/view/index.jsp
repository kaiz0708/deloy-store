<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../css/reset.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
    />
    <link rel="stylesheet" href="../css/magnify.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>GreatGreen</title>
  </head>
  <body>
    <header>
      <div class="box">
        <div class="logo">
          <a href="" class="logo-box">
            <img src="./img/logo.jpg" alt="" />
          </a>
        </div>
        <div class="menu">
          <ul>
            <li><a href="toHome">HOME</a></li>
            <li><a href="loadProduct" methods="POST">PRODUCT</a></li>
          </ul>
        </div>
        <div class="search">
          <form action="searchProduct" class="search-box">
            <input
              type="text"
              class="search-text"
              name = "search"
              placeholder="Search"
              required
            />
            <button class="search-btn">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </form>
        </div>
        <div class="shop">
          <a onclick="loadCart()">
            <i class="fa-solid fa-basket-shopping">
            </i>
          </a>
        </div>
        <div class="user" onclick="onOfUseroptions()">
          <a href="#" class="user-box">
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
    <div id="container">
      <!-- Banner -->
      <div class="banner">
        <img src="./img/banner6.jpg" alt="" />
        <div class="banner-content">
          <h3><span>Clean Agricultural Products</span></h3>
          <div class="banner-subcontent">
            <p>
              We are committed to providing clean, quality agricultural foods to serve your lifestyle needs.
            </p>
          </div>
          <button type="button" class="banner-btn">About</button>
        </div>
      </div>
      <!-- End banner -->
      <!-- Slider -->
      <div class="slider">
        <div class="slider-container active">
          <div class="slide">
            <div class="content">
              <span>Bell Pepper</span>
              <h3>Bell Pepper</h3>
              <p>
                Bell pepper, also known as sweet pepper or capsicum, is a colorful and flavorful vegetable that belongs to the nightshade family. It is characterized by its bell-like shape, with a flat bottom and a slightly pointed top. The pepper's size can vary, and it comes in a range of vibrant colors, including red, green, yellow, and orange.
              </p>
              <a href="" class="slider-btn">MORE</a>
            </div>
            <div class="img">
              <img
                src="./img/slide/1.png"
                alt=""
                class="img-shoe zoom"
                data-magnify-src="./img/slide/1.png"
              />
            </div>
          </div>
        </div>
        <div class="slider-container">
          <div class="slide">
            <div class="content">
              <span>Bell Pepper</span>
              <h3>Bell Pepper</h3>
              <p>
                The smooth and glossy skin of the bell pepper encases a crisp and juicy flesh. The taste is mildly sweet, making it a popular choice for various culinary applications. Bell peppers are not spicy like their hot pepper counterparts, such as chili peppers.
              </p>
              <a href="" class="slider-btn">MORE</a>
            </div>
            <div class="img">
              <img
                src="./img/slide/2.png"
                alt=""
                class="img-shoe zoom"
                data-magnify-src="./img/slide/2.png"
              />
            </div>
          </div>
        </div>
        <div class="slider-container">
          <div class="slide">
            <div class="content">
              <span>Bell Pepper</span>
              <h3>Bell Pepper</h3>
              <p>
                In addition to their delightful taste, bell peppers are rich in essential nutrients, particularly vitamin C, vitamin A, and various antioxidants. These nutrients contribute to the vegetable's health benefits, supporting immune function and providing antioxidant protection.
              </p>
              <a href="" class="slider-btn">MORE</a>
            </div>
            <div class="img">
              <img
                src="./img/slide/3.png"
                alt=""
                class="img-shoe zoom"
                data-magnify-src="./img/slide/3.png"
              />
            </div>
          </div>
        </div>
        <div class="slider-container">
          <div class="slide">
            <div class="content">
              <span>Bell Pepper</span>
              <h3>Bell Pepper</h3>
              <p>
                Bell peppers are versatile in the kitchen and can be enjoyed raw in salads, sliced as a crunchy snack, or cooked in a variety of dishes such as stir-fries, fajitas, and stuffed peppers. Their vibrant colors not only add visual appeal to meals but also indicate different stages of ripeness, with green being the least ripe and red the fully mature stage. Overall, bell peppers are a nutritious and flavorful addition to a well-balanced diet.
              </p>
              <a href="" class="slider-btn">Add to cart</a>
            </div>
            <div class="img">
              <img
                src="./img/slide/4.png"
                alt=""
                class="img-shoe zoom"
                data-magnify-src="./img/slide/4.png"
              />
            </div>
          </div>
        </div>
        <div class="previous-slide" onclick="previousSlide()">
          <i class="fa-solid fa-arrow-left"></i>
        </div>
        <div class="next-slide" onclick="nextSlide()">
          <i class="fa-solid fa-arrow-right"></i>
        </div>
      </div>
      <!-- End Slider -->
      <!-- Lastest Product -->
      <div class="latest-product">
        <h1 class="title">SALE <span>OFF</span></h1>
        <div class="product-container">
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/1.jpg" alt="" class="product-img" />
              <h3>Rice</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/6.png" alt="" class="product-img" />
              <h3>Eggplant</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VNd</div>
            </div>
          </div>
        </div>
      </div>
      <!-- End lastest Product -->
      <!-- New Product -->
      <!-- Reuse lastest product for new product -->
      <div class="latest-product">
        <h1 class="title">New <span>product</span></h1>
        <div class="product-container">
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/5.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/6.png" alt="" class="product-img" />
              <h3>Eggplant</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND></div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
          <div class="product-item">
            <div class="product-content">
              <img src="./img/product4/7.png" alt="" class="product-img" />
              <h3>Spinach</h3>
              <div class="price">200.000VND</div>
            </div>
          </div>
        </div>
      </div>
      <!-- End New Product -->
      <!-- New Product -->
<%--      <div class="newProduct">--%>
<%--        <h1 class="title">BEST <span>SELLER</span></h1>--%>
<%--        <div class="newProductContainer">--%>
<%--          <div class="newProductItem">--%>
<%--            <div class="imgBox">--%>
<%--              <div class="imgBoxContent">--%>
<%--                <div class="img">--%>
<%--                  <img--%>
<%--                    src="./img/productred/1.jpg"--%>
<%--                    alt=""--%>
<%--                    class="imgFeature1"--%>
<%--                  />--%>
<%--                </div>--%>
<%--                <div class="img">--%>
<%--                  <img--%>
<%--                    src="./img/productred/2.jpg"--%>
<%--                    alt=""--%>
<%--                    class="imgFeature1"--%>
<%--                  />--%>
<%--                </div>--%>
<%--                <div class="img">--%>
<%--                  <img--%>
<%--                    src="./img/productred/3.jpg"--%>
<%--                    alt=""--%>
<%--                    class="imgFeature1"--%>
<%--                  />--%>
<%--                </div>--%>
<%--                <div class="img">--%>
<%--                  <img--%>
<%--                    src="./img/productred/4.jpg"--%>
<%--                    alt=""--%>
<%--                    class="imgFeature1"--%>
<%--                  />--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--            <div class="newProductBox">--%>
<%--              <div class="productImg">--%>
<%--                <div class="productImgContent">--%>
<%--                  <img src="./img/productred/1.jpg" alt="" class="bigImg1" />--%>
<%--                </div>--%>
<%--              </div>--%>
<%--              <div class="productContent">--%>
<%--                <h3 class="productName">NEW NIKE AIRMAC SHOES</h3>--%>
<%--                <div class="sizeBox">--%>
<%--                  <p>Size</p>--%>
<%--                  <div class="sizeBoxoption">--%>
<%--                    <div--%>
<%--                      class="sizeOption size37 active"--%>
<%--                      onclick="changeSize(37)"--%>
<%--                    >--%>
<%--                      37--%>
<%--                    </div>--%>
<%--                    <div class="sizeOption size38" onclick="changeSize(38)">--%>
<%--                      38--%>
<%--                    </div>--%>
<%--                    <div class="sizeOption size39" onclick="changeSize(39)">--%>
<%--                      39--%>
<%--                    </div>--%>
<%--                    <div class="sizeOption size40" onclick="changeSize(40)">--%>
<%--                      40--%>
<%--                    </div>--%>
<%--                    <div class="sizeOption size41" onclick="changeSize(41)">--%>
<%--                      41--%>
<%--                    </div>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <p>--%>
<%--                  Lorem ipsum, dolor sit amet consectetur adipisicing elit.--%>
<%--                  Dignissimos, repellendus magni alias, quibusdam rerum placeat--%>
<%--                  sit ipsa velit dolorum nostrum hic laudantium commodi at quod--%>
<%--                  voluptatem illum quae! Deserunt, optio!--%>
<%--                </p>--%>
<%--                <div class="colorBox">--%>
<%--                  <p>Color</p>--%>
<%--                  <div class="colorBoxOption">--%>
<%--                    <div--%>
<%--                      class="colorOption color1 active"--%>
<%--                      id="red"--%>
<%--                      onclick="changecolor('red')"--%>
<%--                    ></div>--%>
<%--                    <div--%>
<%--                      class="colorOption color2"--%>
<%--                      id="green"--%>
<%--                      onclick="changecolor('green')"--%>
<%--                    ></div>--%>
<%--                    <div--%>
<%--                      class="colorOption color3"--%>
<%--                      id="black"--%>
<%--                      onclick="changecolor('black')"--%>
<%--                    ></div>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="price">--%>
<%--                  <div class="price">200.000VND <span>150.000VND</span></div>--%>
<%--                  <button class="cartBtn">ADD TO CART</button>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
      <!-- End new product -->
      <!-- Best Seller -->
      <!-- <div class="bestSeller">
                    <div class="bestSellerBox">
                        <h1 class="heading">Best <span>Seller</span></h1>
                        <div class="bestSellerContainer">
                            <div class="bestSellerContent">
                                <h1 class="title">MENS</h1>
                                <div class="name">Red Nike Jordan Max Aura <span class="nameOverLay"></span></div>
                                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Numquam, et itaque pariatur voluptatibus sint, vel quia soluta quaerat ducimus repellendus ipsa unde harum, consectetur eveniet nulla praesentium commodi sed a!</p>
                                <div class="smallImg">
                                    <div class="smallImgBox">
                                        <img src="./img/showcase/shoe1-1/img1.png" alt="">
                                    </div>
                                    <div class="smallImgBox">
                                        <img src="./img/showcase/shoe1-1/img2.png" alt="">
                                    </div>
                                    <div class="smallImgBox">
                                        <img src="./img/showcase/shoe1-1/img3.png" alt="">
                                    </div>
                                    <div class="smallImgBox">
                                        <img src="./img/showcase/shoe1-1/img4.png" alt="">
                                    </div>
                                </div>
                            </div>
                            <div class="bestSellerMainImg">
                                <div class="mainImgBox">
                                    <img src="./img/showcase/shoe1-1/img1.png" alt="">
                                </div>
                            </div>
                            <div class="bestSellerOption">
                                <div class="size">
                                    <p>Size</p>
                                    <ul class="sizeOption">
                                        <li class="sizeActive">37</li>
                                        <li>38</li>
                                        <li>39</li>
                                        <li>40</li>
                                        <li>41</li>
                                    </ul>
                                </div>
                                <div class="star">
                                    <p>Reviews</p>
                                    <ul class="starBox">
                                        <li class="startActive"><i class="fa-solid fa-star"></i></li>
                                        <li class="startActive"><i class="fa-solid fa-star"></i></li>
                                        <li class="startActive"><i class="fa-solid fa-star"></i></li>
                                        <li class="startActive"><i class="fa-solid fa-star"></i></li>
                                        <li><i class="fa-regular fa-star"></i></li>
                                    </ul>
                                </div>
                                <div class="price">
                                    <p>Price</p>
                                    <div class="priceShoe">$150</div>
                                </div>
                                <div class="colorOption">
                                    <p>Colors</p>
                                    <div class="color1 colorActive"></div>
                                    <div class="color2"></div>
                                </div>
                            </div>
                            <div class="barBottomControl">
                                <i class="fa-solid fa-heart"></i>
                                <div class="controlBox">
                                    <div class="previousBestSellerProduct" onclick=""><i class="fa-solid fa-arrow-left"></i></div>
                                    <small class="numberShoe">01</small>
                                    <div class="pagination">
                                        <div class="pag pagActive"></div>
                                        <div class="pag"></div>
                                        <div class="pag"></div>
                                    </div>
                                    <small class="numberShoe">03</small>
                                    <div class="nextBestSellerProduct" onclick=""><i class="fa-solid fa-arrow-right"></i></div>
                                </div>
                                <button class="bestSellerCartBtn">ADD TO CART</button>
                            </div>
                        </div>
                    </div>
        </div> -->
      <!-- End Best seller -->
      <!-- Trending Product -->
      <!-- <div class="trending-box">
                    <div class="title">
                        HOT SALE
                    </div>
                    <div class="detail">
                        <div class="info">
                            <h2>
                                Product summer hot sale 2022
                            </h2>
                            <div class="des">
                                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis harum soluta, vitae quis nobis, iste maiores sed ratione ipsum praesentium nesciunt nostrum. Aspernatur, nam maxime. Ad officiis facere hic qui!
                            </div>
                        </div>
                        <div class="img">
                            <img src="./img/blackShoes.png" alt="">
                        </div>
                        <div class="option">
                            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Culpa temporibus ex praesentium voluptatum autem numquam soluta, voluptates beatae? At consectetur vel eligendi explicabo eveniet aspernatur molestias consequatur temporibus nisi reiciendis.
                            <br>
                            <b>Color</b> 
                            <br>
                            <div class="ellipse" id="black" style="background-color: #555">
                            </div>
                            <div class="ellipse" id="green" style="background-color: #98FB98;">
                            </div>
                            <div class="ellipse" style="background-color: brown">
                            </div>
                            <br>
                            <button class="card">ADD TO CART</button>
                        </div>
                    </div>
        </div> -->
      <!-- End Trending Product -->
      <!-- Category -->
      <div class="category-box">
        <h2>MORE TO <span>EXPLORE</span></h2>
        <div class="category-item">
          <img src="./img/women.jfif" alt="" />
          <button type="button" class="category-btn">Bean</button>
        </div>
        <div class="category-item">
          <img src="./img/men.jfif" alt="" />
          <button type="button" class="category-btn">Tubers</button>
        </div>
        <div class="category-item">
          <img src="./img/kid.jfif" alt="" />
          <button type="button" class="category-btn">Vegetable</button>
        </div>
      </div>
      <!-- End Category -->
    </div>
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
          <p>©2023 GreatGreen. All Rights Reserved.</p>
        </div>
      </div>
    </div>
    <!-- End footer -->
    <script src="./js/jquery.magnify.js"></script>
    <script src="./js/main.js"></script>
    <script src="./js/products.js"></script>
    <script>
      $(document).ready(function () {
        $(".zoom").magnify();
      });
    </script>
  </body>
</html>
