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
                                <a href="home">Home</a>
                            </li>
                            <li class="active">Shop </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--Shop-->                    
            <div class="shop-area pt-120 pb-120">
                <div class="container">
                    <div class="row flex-row-reverse">
                        <div class="col-lg-9">
                            <!--Shop Top Right-->
                            <div class="shop-topbar-wrapper">
                                <div class="shop-topbar-left">
                                    <div class="view-mode nav">
                                        <a class="active" href="#shop-1" data-toggle="tab"><i class="icon-grid"></i></a>
                                    </div>
                                    <p>Showing 1 - ${PAGE_SIZE} of ${totalProduct} results </p>
                                </div>
                                <div class="product-sorting-wrapper">
                                    <div class="product-shorting shorting-style">
                                        <label>View :</label>
                                        <!--Optione Page Size Menu-->
                                        <c:if test="${sessionScope.backToUrl eq \"menu\"}">
                                            <form action="menu" method="POST" >
                                                <select name="viewPage" onchange="this.form.submit()">
                                                    <option value="9" ${PAGE_SIZE == 9 ? " selected":""}> 9</option>
                                                    <option value="18" ${PAGE_SIZE == 18 ? " selected":""}> 18</option>
                                                    <option value="20" ${PAGE_SIZE == 20 ? " selected":""}> 20</option>
                                                </select>
                                            </form>
                                        </c:if>
                                        <!--Optione Page Size category-->
                                        <c:if test="${sessionScope.backToUrl eq \"menu?do=fillCategory\"}">
                                            <form action="menu?do=fillCategory" method="POST" >
                                                <input name="categoryID" type="hidden" value="${cateID}">
                                                <select name="viewPage" onchange="this.form.submit()">
                                                    <option value="6" ${PAGE_SIZE == 6 ? " selected":""}> 6</option>
                                                    <option value="12" ${PAGE_SIZE == 12 ? " selected":""}> 12</option>
                                                    <option value="15" ${PAGE_SIZE == 15 ? " selected":""}> 15</option>
                                                </select>
                                            </form>
                                        </c:if>
                                        <!--Optione Page Size Search-->
                                        <c:if test="${sessionScope.backToUrl eq \"menu?do=search\"}">
                                            <form action="menu?do=search" method="POST" >
                                                <input name="searchKey" type="hidden" value="${keySearch}">
                                                <select name="viewPage" onchange="this.form.submit()">
                                                    <option value="6" ${PAGE_SIZE == 6 ? " selected":""}> 6</option>
                                                    <option value="12" ${PAGE_SIZE == 12 ? " selected":""}> 12</option>
                                                    <option value="15" ${PAGE_SIZE == 15 ? " selected":""}> 15</option>
                                                </select>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!--Shop button right-->
                            <div class="shop-bottom-area">
                                <div class="tab-content jump">
                                    <!--Shop 1-->
                                    <div id="shop-1" class="tab-pane active">
                                        <div class="row">
                                            <c:forEach items="${listProduct}" var="pro">
                                                <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
                                                    <div class="single-product-wrap mb-35">
                                                        <div class="product-img product-img-zoom mb-15">
                                                            <a href="details?do=details&pid=${pro.productID}">
                                                                <img src="${pro.imageURL}" alt="" style="min-height: 300px; max-height: 300px;  ">
                                                            </a>
                                                            <c:if test="${pro.discount !=0}">
                                                                <span class="pro-badge left bg-red">Sale ${pro.discount*100}%</span>
                                                            </c:if>
                                                            <div class="product-action-2 tooltip-style-2">
                                                                <button title="Quick View" data-toggle="modal" data-target="#exampleModal${pro.productID}"><i class="icon-size-fullscreen icons"></i></button>
                                                                <button title="Compare"><a href="compareProduct?do=compareProduct&pId=${pro.productID}"><i class="icon-refresh"></i></a></button>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap-2 text-center">
                                                            <h3><a href="details?do=details&pid=${pro.productID}">${pro.productName}</a></h3>
                                                            <div class="pro-list-price product-price-2">
                                                                <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                                                    <span class="text-muted old-price">$${pro.unitPrice}</span>
                                                                </c:if>
                                                                <spam class="new-price">$${pro.priceAferDiscount}</spam>
                                                            </div>
                                                        </div>
                                                        <div class="product-content-wrap-2 product-content-position text-center">
                                                            <h3><a href="details?do=details&pid=${pro.productID}">${pro.productName}</a></h3>
                                                            <div class="pro-list-price product-price-2">
                                                                <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                                                    <span class="text-muted old-price">$${pro.unitPrice}</span>
                                                                </c:if>
                                                                <spam class="new-price">$${pro.priceAferDiscount}</spam>
                                                            </div>
                                                            <div class="pro-add-to-cart">
                                                                <button  title="Add to Cart"><a href="AddToCart?pid=${pro.productID}" style="text-decoration: none; color: white;">Add To Cart</a></button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--Modal-->
                                                <div class="modal fade" id="exampleModal${pro.productID}" tabindex="-1" role="dialog">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="row">
                                                                    <div class="col-lg-5 col-md-6 col-12 col-sm-12">
                                                                        <div class="tab-content quickview-big-img">
                                                                            <div id="pro-1" class="tab-pane fade show active">
                                                                                <img src="${pro.imageURL}" alt="" style="min-height: 400px;">
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-lg-7 col-md-6 col-12 col-sm-12">
                                                                        <div class="product-details-content quickview-content">
                                                                            <h2>${pro.productName}</h2>
                                                                            <p>${pro.description}</p>
                                                                            <div class="pro-list-price product-price-2 pro-details-price">
                                                                                <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                                                                    <span class="text-muted old-price">$${pro.unitPrice}</span>
                                                                                </c:if>
                                                                                <spam class="new-price">$${pro.priceAferDiscount}</spam>
                                                                            </div>


                                                                            <div class="pro-details-action-wrap">
                                                                                <div class="pro-details-add-to-cart">
                                                                                    <a title="Add to Cart" href="AddToCart?pid=${pro.productID}">Add To Cart </a>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>   
                                                <!-- Modal end -->
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <!--Paging Product-->
                                <c:if test="${sessionScope.backToUrl eq \"menu\"}">
                                    <c:choose>
                                        <c:when test="${listProduct == null || listProduct.size() == 0}">
                                            Not FOUND
                                        </c:when>
                                        <c:otherwise>
                                            <div class="pro-pagination-style text-center mt-10">
                                                <ul>
                                                    <c:if test="${page > 1}">
                                                        <li><a class="prev" href="menu?do=menu&page=${page - 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-left"></i></a></li>
                                                            </c:if>
                                                            <c:if test="${totalPage != 1}">
                                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                            <li><a class="${i == page?" active":""}" href="menu?do=menu&page=${i}&viewPage=${PAGE_SIZE}">${i}</a></li>
                                                            </c:forEach>   
                                                        </c:if>
                                                        <c:if test="${page < totalPage}">
                                                        <li><a class="next" href="menu?do=menu&page=${page + 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-right"></i></a></li>
                                                            </c:if>
                                                </ul>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <!--End paging Category-->
                                <!--Paging Category-->
                                <c:if test="${sessionScope.backToUrl eq \"menu?do=fillCategory\"}">
                                    <c:choose>
                                        <c:when test="${listProduct == null || listProduct.size() == 0}">
                                            Not FOUND
                                        </c:when>
                                        <c:otherwise>
                                            <div class="pro-pagination-style text-center mt-10">
                                                <ul>
                                                    <c:if test="${page > 1}">
                                                        <li><a class="prev" href="menu?do=fillCategory&categoryID=${cateID}&page=${page - 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-left"></i></a></li>
                                                            </c:if>
                                                            <c:if test="${totalPage != 1}">
                                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                            <li><a class="${i == page?" active":""}" href="menu?do=fillCategory&categoryID=${cateID}&page=${i}&viewPage=${PAGE_SIZE}">${i}</a></li>
                                                            </c:forEach>   
                                                        </c:if>
                                                        <c:if test="${page < totalPage}">
                                                        <li><a class="next" href="menu?do=fillCategory&categoryID=${cateID}&page=${page + 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-right"></i></a></li>
                                                            </c:if>
                                                </ul>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <!--End paging Category-->
                                <!--Paging Search-->
                                <c:if test="${sessionScope.backToUrl eq \"menu?do=search\"}">
                                    <c:choose>
                                        <c:when test="${listProduct == null || listProduct.size() == 0}">
                                            Not FOUND
                                        </c:when>
                                        <c:otherwise>
                                            <div class="pro-pagination-style text-center mt-10">
                                                <ul>
                                                    <c:if test="${page > 1}">
                                                        <li><a class="prev" href="menu?do=search&searchKey=${keySearch}&page=${page - 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-left"></i></a></li>
                                                            </c:if>
                                                            <c:if test="${totalPage != 1}">
                                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                            <li><a class="${i == page?" active":""}" href="menu?do=search&searchKey=${keySearch}&page=${i}&viewPage=${PAGE_SIZE}">${i}</a></li>
                                                            </c:forEach>   
                                                        </c:if>
                                                        <c:if test="${page < totalPage}">
                                                        <li><a class="next" href="menu?do=search&searchKey=${keySearch}&page=${page + 1}&viewPage=${PAGE_SIZE}"><i class="icon-arrow-right"></i></a></li>
                                                            </c:if>
                                                </ul>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <!--End paging search-->
                            </div>
                        </div>
                        <!--Left Product-->
                        <div class="col-lg-3">
                            <div class="sidebar-wrapper sidebar-wrapper-mrg-right">
                                <!--Search-->
                                <div class="sidebar-widget mb-40">
                                    <h4 class="sidebar-widget-title">Search </h4>
                                    <div class="sidebar-search">
                                        <form class="sidebar-search-form" action="menu?do=search" method="POST">
                                            <input name="searchKey" type="text" placeholder="Search here..." value="${keySearch}">
                                            <button type="submit"><i class="icon-magnifier"></i></button>
                                        </form>
                                    </div>  
                                </div>
                                <!--Category-->
                                <div class="sidebar-widget shop-sidebar-border mb-35 pt-40">
                                    <h4 class="sidebar-widget-title">Categories </h4>
                                    <div class="shop-catigory">
                                        <ul>
                                            <li class="list-group-item btn btn-outline-primary ms-lg-1 border border-2 rounded-pill">
                                                <a href="menu?do=fillCategory&categoryID=-1" style="text-decoration: none; color: black;">
                                                    All
                                                </a> 
                                            </li>
                                            <c:forEach items="${sessionScope.listCategory}" var="listCate">
                                                <li class="list-group-item btn btn-outline-primary ms-lg-1 border border-2 rounded-pill ${listCate.categoryID == cateID ?" active":""}">
                                                    <a href="menu?do=fillCategory&categoryID=${listCate.categoryID}" style="text-decoration: none; color: black;">
                                                        ${listCate.categoryName}
                                                    </a> 
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
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
