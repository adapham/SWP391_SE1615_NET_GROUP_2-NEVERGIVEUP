<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>

    <body>

        <div class="main-wrapper">
            <header class="header-area transparent-bar section-padding-1">
                <div class="container-fluid">
                    <div class="header-large-device">
                        <div class="header-bottom">
                            <div class="row align-items-center">

                                <div class="col-xl-12">
                                    <div class="header-action header-action-flex header-action-mrg-right">

                                        <c:if test="${sessionScope.Account ==null}">
                                            <div class="same-style-2">
                                                <div class="dropdown show">
                                                    <a class=" dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class="icon-user"></i></a>
                                                    </a>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                                        <a class="dropdown-item" href="login?do=login">Login</a>
                                                        <a class="dropdown-item" href="login?do=Register">Register</a>

                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.Account !=null}">
                                            <div class="same-style-2">
                                                <div class="dropdown show">
                                                    <a class=" dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <img src="${sessionScope.Account.imageURL}" class="rounded-circle" alt="A girl" height="30" width="30"/> ${sessionScope.Account.displayname} 
                                                    </a>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                                        <a class="dropdown-item" href="login?do=logout">Logout</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>       
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div class="breadcrumb-area bg-gray">
                <div class="container">
                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="home">Home</a>
                            </li>
                            <li class="active">Cart Page </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="cart-main-area pt-115 pb-120">
                <div class="container">

                    <div class="row">
                                <div class="col-md-4">
                                    <h3 style="color: black"><b> Information customer:  </b></h3>
                                    <p style="color: black"><b>Order date: </b>${info.orderDate}</p> 
                                    <p style="color: black"><b>Customer name: </b>${info.displayname}</p> 
                                    <p style="color: black"><b>Address: </b>${info.address}</p> 
                                    <p style="color: black"><b>Email: </b>${info.email}</p> 
                                    <p style="color: black"><b>Phone: </b>${info.phone}</p>
                                </div>
                                <div class="col-md-8  table-content table-responsive cart-table-content">
                                     <h3 style="color: black"><b> Information bill  </b></h3>
                                    <table >
                                         
                                        <thead>
                                            <tr>                                            
                                                <th style="color: black">ProductName</th>
                                                <th style="color: black">Price</th>   
                                                <th style="color: black">Quantity</th>
                                                <th style="color: black">Discount</th>                                          
                                                <th style="color: black">Total</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach items="${list}" var="l">
                                                <tr>
                                                    <td style="color: black">${l.productName}</td>
                                                    <td style="color: black">${l.price}$</td>
                                                    <td style="color: black">${l.quantity}</td>
                                                    <td style="color: black">${l.discount}</td>                                                
                                                    <td style="color: black">${Math.round(l.total*100)/100}$</td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>

                                    </table>
                              

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