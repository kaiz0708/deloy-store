<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <!-- <link rel="stylesheet" href="./assets/css/reset.css"> -->
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="font.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> 
    <link rel="apple-touch-icon" sizes="57x57" href="./assets/img/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="./assets/img/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="./assets/img/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="./assets/img/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="./assets/img/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="./assets/img/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="./assets/img/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="./assets/img/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="./assets/img/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./assets/img/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="./assets/img/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./assets/img/favicon/favicon-16x16.png">
    <!-- <link rel="manifest" href="/manifest.json"> -->
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
</head>
<body>
    <section>
        <!--Bắt Đầu Phần Hình Ảnh-->
        <div class="img-bg">
            <img src="https://firebasestorage.googleapis.com/v0/b/tunetowntest-e968a.appspot.com/o/agricultural%2FgreenShoes.png?alt=media" alt="Hình Ảnh Minh Họa">
        </div>
        <!--Kết Thúc Phần Hình Ảnh-->
        <!--Bắt Đầu Phần Nội Dung-->
        <div class="content">
            <div class="form">
                <h1>Login Page</h1>
                <form class="login_form" action="logIn" method="post" name="form">
                    <div class="input-wrapper">
                        <div class="font ">Phone Number </div>
                        <input autocomplete="off" type="text" class="input" name="phonenumber" placeholder="Please enter your Phone Number">
                        <div id="phone_error">You must enter phone number</div>
                        <div class="font font2 ">Password </div>
                        <input type="password" name="userPassword" placeholder="Please enter your Password" autocomplete="off">
                        <div id="pass_error">You must enter password</div>
                    </div>
                    <div class="remember-sign-in">
                        <label><input type="checkbox" name=""> Remember Sign In</label>
                    </div>
                    <div class="input-form">
                        <input type="submit" value="Log in">
                    </div>
                    <div class="input-form">
                        <p>${message}</p>
                    </div>
                    <div class="input-form">
                        <p>Do not have an account? <a href="toRegister">Register</a></p>
                    </div>
                    <div class="input-form">
                        <p>Do not remember password? <a href="toForgetPassword">Forgot Password</a></p>
                    </div>
                </form>
                <h2>Social Login</h2>
                <ul class="icon-login">
                    <li><i class="fa fa-facebook" aria-hidden="true"></i></li>
                    <li><i class="fa fa-google" aria-hidden="true"></i></li>
                    <li><i class="fa fa-twitter" aria-hidden="true"></i></li>
                </ul>
            </div>
        </div>
        <!--Kết Thúc Phần Nội Dung-->
    </section>
    <script src="./js/login.js"></script>
</body>
</html>