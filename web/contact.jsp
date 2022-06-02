<%-- 
    Document   : blogList
    Created on : May 25, 2022, 10:01:18 PM
    Author     : Window 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/cssContact.css" rel="stylesheet" />
        <link href="css/main.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5 d-flex justify-content-center">
                <a class="navbar-brand" href="home?do=home">Foodie</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home?do=blogList">Blog List</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=home">Menu</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=contact">Contact</a></li>
                    </ul>
                    

                </div>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item dropdown">
                    <c:if test="${sessionScope.Account ==null}">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false"> <img src="https://cdn.iconscout.com/icon/free/png-256/account-avatar-profile-human-man-user-30448.png" height="40px" width="70%"/>  
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.Account !=null}">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false"> <img src="${sessionScope.Account.imageURL}" class="rounded-circle" alt="A girl" width="50"/>  
                        </a> ${sessionScope.Account.displayname}  
                    </c:if>
                    <c:if test="${sessionScope.Account ==null}">
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="login">Login</a></li>
                            <li><a class="dropdown-item" href="login?do=Register">Regsiter</a></li>  
                        </ul>   
                    </c:if>
                    <c:if test="${sessionScope.Account !=null}">
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="login?do=logout">Logout</a></li>
                            <li><a class="dropdown-item" href="login?do=updateprofile">Update Profile</a></li>  
                        </ul>   
                    </c:if>

                    </li>
                </ul>
            </div>
        </nav>
        <!-- Header-->

        <div class="row" id="contatti">
            <div class="container mt-5" >

                <div class="row" style="height:550px;">
                    <div class="col-md-6" >
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.485409531582!2d105.52487561420169!3d21.01325499368497!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2sFPT%20University!5e0!3m2!1sen!2s!4v1653505211260!5m2!1sen!2s" width="650" height="300" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                    <div class="col-md-6">                        
                        <div class="text-white">
                            <h2 class="text-uppercase mt-4 font-weight-bold">Foodie</h2>
                            <i class="fas fa-globe mt-3"></i> <a>Đất thổ cư Hòa Lạc</a><br>
                            <i class="fas fa-globe mt-3"></i> <a>Km29 đường cao tốc 08</a><br>
                            <i class="fa fa-globe mt-3"></i> <a>Thạch Hòa</a><br>
                            <i class="fa fa-globe mt-3"></i> <a>Thạch Thất</a><br>
                            <i class="fa fa-globe mt-3"></i> <a>Hà Nội</a><br>
                            <i class="fas fa-globe mt-3"></i>Phone: 0983697804<br>                                
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <%@include file="component/FooterHome.jsp" %>
    </body>
</html>

