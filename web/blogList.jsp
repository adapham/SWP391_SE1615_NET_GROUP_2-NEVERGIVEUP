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
        <%@include file="component/NavbarHome.jsp" %>
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
