<%-- 
    Document   : shop.jsp
    Created on : Jun 8, 2022, 12:22:51 AM
    Author     : KhacBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">

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
            <!--Header-->
            <%@include file="component/HeaderComponent.jsp" %>
            <!-- Mobile menu start -->
            <div class="breadcrumb-area bg-gray">
                <div class="container">
                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="menu">Shop</a>
                            </li>
                            <li class="active">Compare Product </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--Shop-->                    
            <div class="shop-area pt-120 pb-120">
                <div class="container">
                    <div class="alert alert-info" role="alert">
                        <h1 style="display: inline-block;">Compare products:</h1><span style="font-size: 1.5rem;">Search for other products to compare</span>
                    </div>
                    <div class="row">
                        <!--Left-->
                        <div class="col col-md-6">
                            <table class="table table-bordered ">
                                <tbody class="text-center">
                                    <c:forEach items="${listProduct}" var="l">
                                        <tr>
                                            <th >Name</th>
                                            <td colspan="2">${l.productName}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Image</th>
                                            <td colspan="2">
                                                <img src="${l.imageURL}" alt="" style="min-height: 300px; max-height: 300px;  ">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Quantity</th>
                                            <td colspan="2">${l.quantity}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">UnitPrice</th>
                                            <td colspan="2">${l.unitPrice} $</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Discount</th>
                                            <td colspan="2">${l.discount*100}%</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Price After Discount</th>
                                            <td colspan="2">${l.priceAferDiscount} $</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!--Right-->
                        <div class="col col-md-6">
                            <div class="sidebar-wrapper sidebar-wrapper-mrg-right col col-md-12">
                                <!--Search-->
                                <div class="sidebar-widget mb-40">
                                    <div class="sidebar-search" style="margin-top: 0;">
                                        <form class="sidebar-search-form" action="" method="POST">
                                            <input oninput="searchByName(this,${product.categoryID},${product.productID})" name="searchKey" type="text" placeholder="Search here..." value="${keySearch}">
                                            <button type="submit"><i class="icon-magnifier"></i></button>
                                        </form>
                                    </div>  
                                </div>
                            </div>
                            <div class="shop-bottom-area">
                                <div class="col col-md-12" >
                                    <div class="tab-content jump">
                                        <!--Shop 1-->
                                        <div id="shop-1" class="tab-pane active">
                                            <div id="contentCompare"  style="">
                                                
                                            </div>
                                        </div>
                                        <!--end shop 1-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Footer-->
        <%@include file="component/FooterComponent.jsp" %>

    
    <!-- All JS is here
============================================ -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function searchByName(param, param1, param2, param3) {
            var keySearch = param.value.trim();
            var cateId = param1;
            var pId = param2;
            var x = location.origin;
            var s = "<%= getServletContext().getContextPath() %>"
            console.log(x);
            console.log(s);
            $.ajax({
                url: x+s+"/searchCompare",
                type: "get", //send it through get method
                data: {
                    keySearch: keySearch,
                    cateId: cateId,
                    pId: pId
                },
                success: function (data) {
                    var row = document.getElementById("contentCompare");
                    row.innerHTML = data;

                },
                error: function (xhr) {
                    //Do Something to handle error
                }
            });
        }
        ;
    </script>
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
