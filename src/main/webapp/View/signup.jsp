<%-- 
    Document   : signin
    Created on : Feb 18, 2022, 9:14:44 PM
    Author     : Nhut Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Design by foolishdeveloper.com -->
        <title>Sign In</title>

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/stylesignin.css">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/signup.css">

    </head>
    <body>

        <%@include file="/View/layout/header.jsp" %>
        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_6.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="home">Home</a></span> <span>Login</span></p>
                        <h1 class="mb-0 bread">Shop</h1>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="background">
                <div class="shape"></div>
                <div class="shape"></div>
            </div>
            <form action="./signup">
                <h3>Sign Up</h3>

                <label for="username">Username</label>
                <input type="text" placeholder="Username" name="username" >

                <label for="password">Password</label>
                <input type="password" placeholder="Password" name="password"><br>
                <label for="password">Re-Password</label>
                <input type="password" placeholder="Password" name="password"><br>

                <p class="help-block alert-danger">${requestScope.ERROR_LOGIN}</p>

                <a href="home.jsp"><button type="submit" style="color: white; border-radius: 10px; background: orange; " id="btn">Sign Up</button></a>
            </form>
        </div>
    </body>
    <script language="javascript">
        var button = document.getElementById("btn");
        button.onclick = function () {
            alert("Sign Up Success !!!");
        }
    </script>
</html>