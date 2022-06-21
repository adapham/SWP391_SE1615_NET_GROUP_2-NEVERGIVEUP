<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Norda - Minimal eCommerce HTML Template</title>
        <meta name="robots" content="noindex, follow" />
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png">

        <!-- All CSS is here
            ============================================ -->

        <link rel="stylesheet" href="assets/css/vendor/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/vendor/signericafat.css">
        <link rel="stylesheet" href="assets/css/vendor/cerebrisans.css">
        <link rel="stylesheet" href="assets/css/vendor/simple-line-icons.css">
        <link rel="stylesheet" href="assets/css/vendor/elegant.css">
        <link rel="stylesheet" href="assets/css/vendor/linear-icon.css">
        <link rel="stylesheet" href="assets/css/plugins/nice-select.css">
        <link rel="stylesheet" href="assets/css/plugins/easyzoom.css">
        <link rel="stylesheet" href="assets/css/plugins/slick.css">
        <link rel="stylesheet" href="assets/css/plugins/animate.css">
        <link rel="stylesheet" href="assets/css/plugins/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/plugins/jquery-ui.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- Use the minified version files listed below for better performance and remove the files listed above
        <link rel="stylesheet" href="assets/css/vendor/vendor.min.css">
        <link rel="stylesheet" href="assets/css/plugins/plugins.min.css">
        <link rel="stylesheet" href="assets/css/style.min.css"> -->

    </head>

    <body>

        <div class="main-wrapper">
            <%@include file="component/HeaderComponent.jsp" %>
            <div class="breadcrumb-area bg-gray">
                <div class="container">
                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="home">Home</a>
                            </li>
                            <li class="active">about us </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="about-us-area pt-120 pb-120">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-3">
                            <div class="about-us-logo">
                                <img src="assets/images/cooker-img.png" alt="logo" style="display: block; margin-left: auto; margin-right: auto">
                                <h1 style="color: red; text-align: center" >Our <span>Food store</span> </br> Irresistible <span>Delicious</span>
                                </h1>
                            </div>
                        </div>
                        <div class="col-lg-9 col-md-9">
                            <div class="about-us-content">
                                <h3>Introduce</h3>
                                <p>The first Foodie store will be opened in Vietnam in 2022 at FPT University Hanoi.
                                    Since then Foodie has made every effort to provide Vietnamese students and families with delicious meals at reasonable prices.
                                    Up to now, Foodie has had nearly 100 stores in Vietnam spread all over the country.
                                    Foodie not only serves quality fast food but also has to go through a strict food safety and hygiene censorship process.
                                </p>                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
            <div class="banner-area pb-85">
                <div class="container">
                    <div class="row">
                        <c:forEach items="${listProduct}" var="pro">
                            <div class="col-lg-6 col-md-6">
                                <div class="banner-wrap mb-30">
                                    <div class="banner-img banner-img-zoom">
                                        <a href="details?do=details&pid=${pro.productID}"><img src="${pro.imageURL}" alt="" style="max-height:   283px; max-width:   655px" ></a>
                                    </div>
                                    <div class="banner-content-11 banner-content-11-modify">
                                        <h2 style="margin-top: -65%">${pro.productName}</h2>
                                        <p></p>
                                        <div class="btn-style-4">
                                            <a class="hover-red" href="details?do=details&pid=${pro.productID}">Shop now <i class="icon-arrow-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
            <div class="team-area pb-90">
                <div class="container">
                    <div class="section-title mb-45 text-center">
                        <h2>Team Members</h2>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="team-wrapper mb-30">
                                <div class="team-img" style="height: 270px;">
                                    <img src="https://scontent.fhan4-3.fna.fbcdn.net/v/t1.6435-9/65283213_452725832223607_2321629019776221184_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=oTMbMuOtRIkAX-6lCgq&_nc_ht=scontent.fhan4-3.fna&oh=00_AT--4L3nCJUrYtL0Jw68k7nqhS3b1QYQHein9q_rwobUDQ&oe=62C81961" alt="">
                                    <div class="team-action">
                                        <a class="facebook" href="https://www.facebook.com/khacbao07">
                                            <i class="social_facebook"></i>
                                        </a>
                                        <a class="twitter" href="https://twitter.com/POTUS">
                                            <i class="social_twitter"></i>
                                        </a>
                                        <a class="instagram" href="https://www.instagram.com/baonk__07/?hl=en">
                                            <i class="social_instagram"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="team-content text-center">
                                    <h4>Mr. Bao</h4>
                                    <span>Manager</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="team-wrapper mb-30">
                                <div class="team-img">
                                    <img src="https://scontent.fhan3-1.fna.fbcdn.net/v/t1.6435-9/65200617_151530962638024_6849459645744939008_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=174925&_nc_ohc=L94vf-pyahgAX-_FIaR&tn=UjtbTFtl40nTD_jt&_nc_ht=scontent.fhan3-1.fna&oh=00_AT9MloAoUeJeymsshZiI4CPVXPHbDLnBR7U_FR6y-bahdg&oe=62D11A52" alt="">
                                    <div class="team-action">
                                        <a class="facebook" href="https://www.facebook.com/SoftwareEngineering.khai">
                                            <i class="social_facebook"></i>
                                        </a>
                                        <a class="twitter" href="https://twitter.com/DonaldJTrumpJr">
                                            <i class="social_twitter"></i>
                                        </a>
                                        <a class="instagram" href="https://www.instagram.com/ngoc_khai_06/?hl=en">
                                            <i class="social_instagram"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="team-content text-center">
                                    <h4>Mr. Khai</h4>
                                    <span>Security </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="team-wrapper mb-30">
                                <div class="team-img" style="height: 270px;">
                                    <img src="https://scontent.fhan3-1.fna.fbcdn.net/v/t39.30808-6/272959853_1004762727116170_3184609971225838167_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=gsJA8lXDkaAAX-4gfh2&tn=UjtbTFtl40nTD_jt&_nc_ht=scontent.fhan3-1.fna&oh=00_AT_UIh8TsbFmZxM6eWF7lmkDVOpv-Wqo4vcMsFyoUnapBQ&oe=62AE73A3" alt="">
                                    <div class="team-action">
                                        <a class="facebook" href="https://www.facebook.com/phamada2803">
                                            <i class="social_facebook"></i>
                                        </a>
                                        <a class="twitter" href="https://twitter.com/HarryMaguire93">
                                            <i class="social_twitter"></i>
                                        </a>
                                        <a class="instagram" href="https://www.instagram.com/datvilla94/?hl=en">
                                            <i class="social_instagram"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="team-content text-center">
                                    <h4>Ms. Dat</h4>
                                    <span>Staff </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="team-wrapper mb-30">
                                <div class="team-img">
                                    <img src="https://scontent.fhan3-5.fna.fbcdn.net/v/t1.6435-9/107894381_148547816872572_1543076683685639068_n.jpg?stp=c0.8.206.206a_dst-jpg_p206x206&_nc_cat=110&ccb=1-7&_nc_sid=da31f3&_nc_ohc=WLFcoAUV4VAAX_eljAf&_nc_ht=scontent.fhan3-5.fna&oh=00_AT9ifwJfOBbEZszuvWT7tvpaQGIUPbPeE-B3mToWrLcWbA&oe=62D1077C" alt="">
                                    <div class="team-action">
                                        <a class="facebook" href="https://www.facebook.com/tuyen.nguyenvan.46">
                                            <i class="social_facebook"></i>
                                        </a>
                                        <a class="twitter" href="https://twitter.com/Cristiano">
                                            <i class="social_twitter"></i>
                                        </a>
                                        <a class="instagram" href="https://www.instagram.com/tuynnn____/?hl=en">
                                            <i class="social_instagram"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="team-content text-center">
                                    <h4>Mr. Tuyen</h4>
                                    <span>Chef </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>                       
            <%@include file="component/FooterComponent.jsp" %>
        </div>

        <!-- All JS is here
    ============================================ -->

        <script src="assets/js/vendor/modernizr-3.6.0.min.js"></script>
        <script src="assets/js/vendor/jquery-3.5.1.min.js"></script>
        <script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
        <script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
        <script src="assets/js/plugins/slick.js"></script>
        <script src="assets/js/plugins/jquery.syotimer.min.js"></script>
        <script src="assets/js/plugins/jquery.instagramfeed.min.js"></script>
        <script src="assets/js/plugins/jquery.nice-select.min.js"></script>
        <script src="assets/js/plugins/wow.js"></script>
        <script src="assets/js/plugins/jquery-ui-touch-punch.js"></script>
        <script src="assets/js/plugins/jquery-ui.js"></script>
        <script src="assets/js/plugins/magnific-popup.js"></script>
        <script src="assets/js/plugins/sticky-sidebar.js"></script>
        <script src="assets/js/plugins/easyzoom.js"></script>
        <script src="assets/js/plugins/scrollup.js"></script>
        <script src="assets/js/plugins/ajax-mail.js"></script>

        <!-- Use the minified version files listed below for better performance and remove the files listed above  
    <script src="assets/js/vendor/vendor.min.js"></script>
    <script src="assets/js/plugins/plugins.min.js"></script>  -->
        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

    </body>

</html>