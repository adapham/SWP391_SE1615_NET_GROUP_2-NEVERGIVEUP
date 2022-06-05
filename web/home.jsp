
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="css/main.css" rel="stylesheet">
        <style>
            .search-box{
                width: fit-content;
                height: fit-content;
                position: relative;
            }
            .input-search{
                height: 50px;
                width: 50px;
                border-style: none;
                padding: 10px;
                font-size: 18px;
                letter-spacing: 2px;
                outline: none;
                border-radius: 25px;
                transition: all .5s ease-in-out;
                background-color: #22a6b3;
                padding-right: 40px;
                color:#fff;
            }
            .input-search::placeholder{
                color:rgba(255,255,255,.5);
                font-size: 18px;
                letter-spacing: 2px;
                font-weight: 100;
            }
            .btn-search{
                width: 50px;
                height: 50px;
                border-style: none;
                font-size: 20px;
                font-weight: bold;
                outline: none;
                cursor: pointer;
                border-radius: 50%;
                position: absolute;
                right: 0px;
                color:#ffffff ;
                background-color: #00aced;
                pointer-events: painted;  
            }
            .btn-search:focus ~ .input-search{
                width: 300px;
                border-radius: 30px;
                background-color: #00aced;
                border-bottom:1px solid rgba(255,255,255,.5);
                transition: all 500ms cubic-bezier(0, 0.110, 0.35, 2);
            }
            .input-search:focus{
                width: 300px;
                border-radius: 30px;
                background-color: #00aced;
                border-bottom:1px solid rgba(255,255,255,.5);
                transition: all 500ms cubic-bezier(0, 0.110, 0.35, 2);
            }
        </style>
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5 d-flex justify-content-center">
                <a class="navbar-brand" href="home?do=blogList">Foodie</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home?do=blogList">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=home">Menu</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=contact">Contact</a></li>
                    </ul>
                    <!-- Search -->
                    <form class="d-flex mx-auto" action="home?do=search" method="POST">
                        <div class="search-box">
                            <button class="btn-search"><i class="bi bi-search"></i></button>
                            <input oninput="searchByName(this)" name="searchKey" type="text" class="input-search" placeholder="Type to Search...">
                        </div>
                    </form>
                    <!--Cart-->
                    <form class="d-flex">
                        <a class="btn btn-outline-dark" href="cart">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.size}</span>
                        </a>
                    </form>

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
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop in style</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <h3 class="text-center" style="color: red;font-weight: bold; margin-bottom: 20px">Our Menu</h3>
                <!--List Category-->
                <ul class="list-group d-flex flex-row flex-wrap justify-content-center" style="">
                    <li class="list-group-item btn btn-dark ms-lg-1 border border-2 rounded-pill">
                        <a href="home?do=fillCategory&categoryID=-1" style="text-decoration: none; color: black;">
                            All
                        </a> 
                    </li>
                    <c:forEach items="${sessionScope.listCategory}" var="listCate">
                        <li class="list-group-item btn btn-dark ms-lg-1 border border-2 rounded-pill ${listCate.categoryID == cateID ?" active":""}">
                            <a href="home?do=fillCategory&categoryID=${listCate.categoryID}" style="text-decoration: none; color: black;">
                                ${listCate.categoryName}
                            </a> 
                        </li>
                    </c:forEach>
                </ul>
                <br>
                <br>
                <!--List Product-->
                <div id="content" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:choose>
                        <c:when test="${listProduct == null || listProduct.size() == 0}">
                            <span class="d-flex justify-content-center" style="color: red; font-weight: bold; font-size: 30px;">Not Founds</span> 
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listProduct}" var="pro">
                                <div class="product col mb-5">
                                    <div class="card h-100" style="background: #f1f2f3;">
                                        <!-- Sale badge-->
                                        <c:if test="${pro.discount !=0}">
                                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale${pro.discount*100}%</div> 
                                        </c:if>
                                        <!-- Product image-->
                                        <div class="img-box d-flex justify-content-center" style="height: 100%; width: 80%;margin-left: auto; margin-right: auto; margin-top: 10%; margin-bottom: 10%;">
                                            <a href="details?pid=${pro.productID}">
                                                <img class="card-img-top" src="${pro.imageURL}" alt="..."/>
                                            </a>
                                        </div>
                                        <!-- Product details-->
                                        <div class="card-body" style="background: #232831; color: white;">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <a href="details?pid=${pro.productID}">
                                                    <h5 class="fw-bolder">${pro.productName}</h5>
                                                </a>
                                                <!-- Product reviews-->
                                                <div class="d-flex justify-content-center" style="overflow: hidden; max-height: 75px; margin-top: 5px;">
                                                    <span style="text-align: left;">${pro.description}</span>
                                                </div>
                                                <!--Cart-->
                                                <div class="d-flex justify-content-between" style="margin-top: 15px;">
                                                    <!-- Product price-->
                                                    <div class="d-flex align-items-center">
                                                        <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                                            <span class="text-muted text-decoration-line-through">$${pro.unitPrice}</span>
                                                        </c:if>
                                                        $${pro.priceAferDiscount}
                                                    </div>
                                                    <!--Cart-->
                                                    <a class="btn btn-outline-dark mt-auto rounded-circle" href="AddToCart?pid=${pro.productID}" style="font-size: 20px; background-color: #f4bd36;"><i class="bi bi-cart-plus"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>


                </div>
                <!--Paging Home Product-->
                <nav id="paging" aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li class="page-item" ><a class="page-link" href="home?do=home&page=${page - 1}" >Previous</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?" active":""}"><a class="page-link" href="home?do=home&page=${i}">${i}</a></li>
                            </c:forEach>
                            <c:if test="${page < totalPage}">
                            <li class="page-item"><a class="page-link" href="home?do=home&page=${page + 1}">Next</a></li>
                            </c:if>
                    </ul>
                </nav>

                <!--<button onclick="loadMore()" class="btn btn-warning rounded-pill d-flex justify-content-center" style="margin-left: auto;margin-right: auto;padding: 10px 50px;">View More</button>-->
            </div>
        </section>
        <!-- Footer-->
        <%@include file="component/FooterHome.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            var clicks = 2;
            function loadMore() {
                clicks += 1;
                $.ajax({
                    url: "/SWP391_SE1615_NET_GROUP_2-NEVERGIVEUP/load",
                    type: "get", //send it through get method
                    data: {
                        totalPage: clicks //Gửi tới Load Controller
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML += data;

                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }

            function searchByName(param) {
                document.getElementById("paging").style.display = "none";

                var searchKey = param.value;//Get value
                $.ajax({
                    url: "/SWP391_SE1615_NET_GROUP_2-NEVERGIVEUP/searchLoad",
                    type: "get", //send it through get method
                    data: {
                        searchKey: searchKey //Gửi tới Load Controller
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {

                    }
                });
            }
        </script>
    </body>
</html>

