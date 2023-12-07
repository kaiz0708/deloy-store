<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/cart.css">
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
  />
  <link rel="stylesheet" href="./css/profile.css">
    <title>Profile</title>
</head>
<body  style="height: 100%;">
    <!-- Header -->
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
                <li><a href="/loadProduct">PRODUCT</a></li>
                <c:if test="${user.getRoles() == 0}">
                    <li><a href="loadAdminPage">ADMIN</a></li>
                </c:if>

            </ul>
          </div>
          <div class="search">
            <form action="searchProduct" class="search-box">
              <input
                type="text"
                name = "search"
                class="search-text"
                placeholder="Search"
                required
              />
              <button class="search-btn">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </form>
          </div>
          <div class="shop">
            <i class="fa-solid fa-basket-shopping shop-icon"></i>
          </div>
          <div class="user">
            <a href="#" class="user-box" onclick="onOfUseroptions()">
              <img src="./img/namngu.jpg" alt="" />
            </a>
            <div class="user-dropdown">
              <a href="/loadProfile">Profile</a>
              <a href="logOut">Log out</a>
            </div>
          </div>
        </div>
      </header>
      <!-- End Header -->
      <div class="wrapper">
        <div class="leftWrapper">
            <div class="titleLeft">
                <div class="imgBox">
                    <img src="./img/namngu.jpg" alt="">
                </div>
                <p>Change avatar</p>
            </div>
            <div class="bodyLeft">
                <div class="profileOption selectOption " onclick={activeProfileTab()}>
                    <i class="fa-solid fa-user"></i>
                    <p>Profile</p>
                </div>
                <div class="cartOption selectOption" onclick={activeCartTab()}>
                    <i class="fa-solid fa-cart-shopping"></i>
                    <p>Cart</p>
                </div>
                <div class="cartOption selectOption" onclick={activeInvoiceTab()}>
                    <i class="fa-solid fa-cart-shopping"></i>
                    <p>Invoice</p>
                </div>

            </div>
        </div>
        <div class="rightWrapper">
            <div class="profileTab active" >
              <h1 class="titleTab">Profile</h1>
                <form action="updateProfile" method="post">
                    <div class="input-form">
                        <label>Fullname:</label>
                        <input type="text" id="fullname" name="fullname" required value="${user.getFullName()}">
                    </div>
                    
                    <div class="input-form">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required value="${user.getEmail()}">
                    </div>
                    
                    <div class="input-form">
                        <label for="address">Address:</label>
                        <input type="address" id="address" name="address" value="${user.getAddress()}">
                    </div>
                    
                    <div class="input-form">
                        <label for="phonenumber">Phonenumber:</label>
                        <input type="phonenumber" id="phonenumber" name="phonenumber" value="${user.getPhoneNumber()}">
                    </div>
                   
                   <div class="input-form">
                        <input type="submit" value="Update Profile">
                   </div> 
                </form>
            </div>
            <div class="cartTab" >
                <h1 class="titleTab">Cart</h1>
                <form action="checkOut" method="post">
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
                    <tbody id="tbody-cart">

                    </tbody>
                </table>
                    <div class="buttonCalculate">
                        <button type="button" onclick="checkOut(null,null)">Calculate</button>
                    </div>
                </form>
                <div class="totalPriceCart">
                <p>Total: <span>200.000</span> <sub>VND</sub></p>
                </div>


            </div>

            <div class="invoiceTab" >
                <h1 class="titleTab">Invoice</h1>
                <form action="">
                    <table>
                        <thead class="titleTable">
                        <tr>
                            <th class="invoiceId">Invoice ID</th>
                            <th class="imgProduct">Product</th>
                            <th class="nameProduct">Name</th>
                            <th class="priceProduct">Price</th>
                            <th class="amountsProduct">Amounts</th>
                            <th class="addressDelivery">addressDelivery</th>
                        </tr>
                        </thead>
                        <tbody id="tbody-invoice">

                        </tbody>
                    </table>
                </form>
            </div>
        </div>
      </div>
        </div>
      </div>

      <script src="../js/profile.js"></script>
      <script src="../js/main.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>