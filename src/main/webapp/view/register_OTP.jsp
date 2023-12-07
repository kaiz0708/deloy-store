<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    <!-- <link rel="stylesheet" href="./assets/css/reset.css"> -->
    <link rel="stylesheet" href="./css/register.css" />
    <link rel="stylesheet" href="font.css" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link
      rel="apple-touch-icon"
      sizes="57x57"
      href="./assets/img/favicon/apple-icon-57x57.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="60x60"
      href="./assets/img/favicon/apple-icon-60x60.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="72x72"
      href="./assets/img/favicon/apple-icon-72x72.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="76x76"
      href="./assets/img/favicon/apple-icon-76x76.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="114x114"
      href="./assets/img/favicon/apple-icon-114x114.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="120x120"
      href="./assets/img/favicon/apple-icon-120x120.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="144x144"
      href="./assets/img/favicon/apple-icon-144x144.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="152x152"
      href="./assets/img/favicon/apple-icon-152x152.png"
    />
    <link
      rel="apple-touch-icon"
      sizes="180x180"
      href="./assets/img/favicon/apple-icon-180x180.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="192x192"
      href="./assets/img/favicon/android-icon-192x192.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="32x32"
      href="./assets/img/favicon/favicon-32x32.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="96x96"
      href="./assets/img/favicon/favicon-96x96.png"
    />
    <link
      rel="icon"
      type="image/png"
      sizes="16x16"
      href="./assets/img/favicon/favicon-16x16.png"
    />
    <link rel="manifest" href="/manifest.json" />
    <meta name="msapplication-TileColor" content="#ffffff" />
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png" />
    <meta name="theme-color" content="#ffffff" />
  </head>
  <body>
    <section>
      <!--Bắt Đầu Phần Hình Ảnh-->
      <div class="img-bg">
        <img src="./img/RegisterPage/resgister2.jpg" alt="Hình Ảnh Minh Họa" />
      </div>
      <!--Kết Thúc Phần Hình Ảnh-->
      <!--Bắt Đầu Phần Nội Dung-->
      <div class="content">
        <div class="form">
          <h1 class="title-page">Register Page</h1>
          <form
            class="register_form"
            action="verifyOTP"
            method="post"
            name="form"
            id="register-form-id"
          >
            <div class="input-form">
              <span>Enter OTP</span>
              <input type="text" name="otpCode"/>
            </div>
            <div class="input-form">
              <input type="submit" name="otpCode" value="Check"/>
            </div>
            <div id="message">${message}</div>
          </form>
        </div>
      </div>
      <!--Kết Thúc Phần Nội Dung-->
    </section>
    <script src="./js/register.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </body>
</html>
