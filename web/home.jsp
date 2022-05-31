
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
    </head>
    <body>
        <!-- Navigation-->
        <%@include file="component/NavbarHome.jsp" %>
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
                    <c:forEach items="${listCategory}" var="listCate">
                        <li class="list-group-item btn btn-dark ms-lg-1 border border-2 rounded-pill">
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
                    <c:forEach items="${listProduct}" var="pro">
                        <div class="product col mb-5">
                            <div class="card h-100" style="background: #f1f2f3;">
                                <!-- Sale badge-->
                                <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                                <!-- Product image-->
                                <div class="img-box d-flex justify-content-center" style="height: 100%; width: 80%;margin-left: auto; margin-right: auto; margin-top: 10%; margin-bottom: 10%;">
                                    <a href="details?do=homeDetails&productID=${pro.productID}">
                                        <img class="card-img-top" src="${pro.imageURL}" alt="..."/>
                                    </a>
                                </div>
                                <!-- Product details-->
                                <div class="card-body" style="background: #232831; color: white;">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <a href="details">
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
                                                <span class="text-muted text-decoration-line-through">$20.00</span>
                                                ${pro.unitPrice}
                                            </div>
                                            <!--Cart-->
                                            <a class="btn btn-outline-dark mt-auto rounded-circle" href="#" style="font-size: 20px; background-color: #f4bd36;"><i class="bi bi-cart-plus"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!--Paging Home Product-->
                <nav aria-label="Page navigation example">
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

        </script>
    </body>
</html>

