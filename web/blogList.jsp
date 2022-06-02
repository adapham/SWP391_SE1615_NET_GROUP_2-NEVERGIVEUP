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
        <link href="css/cssBlogList.css" rel="stylesheet" />
        <link href="css/main.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: ">
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
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="images/header.jpg" alt=""/>
                </div>
                <div class="carousel-item">
                    <img src="images/subscribe-bg.jpg" alt=""/>
                </div>
                <div class="carousel-item">
                    <img src="images/header.jpg" alt=""/>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!-- about-us start  -->
        <section id="about-us">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <img class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms" src="images/cooker-img.png" alt="cooker-img">
                            <h1 class="heading wow fadeInUp" data-wow-duration="400ms" data-wow-delay="500ms" >Our <span>Food store</span> </br> Irresistible <span>Delicious</span>
                            </h1>
                            <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="600ms">The first Foodie store will be opened in Vietnam in 2022 at FPT University Hanoi. </br> Since then Foodie has made every effort to provide Vietnamese students and families with delicious meals at reasonable prices. </br>Up to now, Foodie has had nearly 100 stores in Vietnam spread all over the country. Foodie not only serves quality fast food but also has to go through a strict food safety and hygiene censorship process.</p>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .containe close -->
        </section>
        <!-- #call-to-action close -->
        <!-- blog start  -->
        <section id="blog">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class="heading">Latest <span>From</span> the <span>Blog</span></h1>
                            <ul>
                                <li class="wow fadeInLeft" data-wow-duration="300ms" data-wow-delay="300ms">
                                    <div class="blog-img">
                                        <img src="images/blog/blog-img-1.jpg" alt="blog-img">
                                    </div>
                                    <div class="content-right">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                </li>
                                <li class="wow fadeInLeft" data-wow-duration="300ms" data-wow-delay="400ms">
                                    <div class="blog-img">
                                        <img src="images/blog/blog-img-2.jpg" alt="blog-img">
                                    </div>
                                    <div class="content-right">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                </li>
                                <li class="wow fadeInLeft" data-wow-duration="300ms" data-wow-delay="500ms">
                                    <div class="content-left">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                    <div class="blog-img-2">
                                        <img src="images/blog/blog-img-3.jpg" alt="blog-img">
                                    </div>
                                </li>
                                <li class="wow fadeInLeft" data-wow-duration="300ms" data-wow-delay="600ms">
                                    <div class="content-left">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                    <div class="blog-img-2">
                                        <img src="images/blog/blog-img-4.jpg" alt="blog-img">
                                    </div>
                                </li>
                                <li class="wow fadeInLeft" data-wow-duration="300ms" data-wow-delay="700ms">
                                    <div class="blog-img">
                                        <img src="images/blog/blog-img-5.jpg" alt="blog-img">
                                    </div>
                                    <div class="content-right">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                </li>
                                <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="800ms">
                                    <div class="blog-img">
                                        <img src="images/blog/blog-img-6.jpg" alt="blog-img">
                                    </div>
                                    <div class="content-right">
                                        <h3>Homestyle Chicken Pot Pie</h3>
                                        <p>Prepared in true New England fash-ion. Tender all-white meat chicken simmered...</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .containe close -->
        </section>
        <!-- #blog close -->
        <!-- price start -->
        <section id="price">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class="heading wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms">our <span>MENU</span> the <span>PRICE</span></h1>
                            <div class="pricing-list">
                                <div class="title">
                                    <h3>Featured <span>FOODS</span></h3>
                                </div>
                                <ul>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Pancakes n' Such</h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 25.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Homestyle Chicken Pot Pie</h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 10.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="500ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Cereal, Panecillos y Frutas </h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 5.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="600ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Meat of Skewers</h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 15.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="700ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Steak with a Garlic and Parsley Risotto</h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 75.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                    <li class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="800ms">
                                        <div class="item">
                                            <div class="item-title">
                                                <h2>Caesar Salad</h2>
                                                <div class="border-bottom"></div>
                                                <span>$ 7.00</span>
                                            </div>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim&&</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .containe close -->
        </section><!-- #price close -->
        <!-- Footer-->
        <%@include file="component/FooterHome.jsp" %>
    </body>
</html>
