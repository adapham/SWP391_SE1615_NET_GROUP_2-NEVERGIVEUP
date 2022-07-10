<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <li class="active">product details</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="product-details-area pt-120 pb-115">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="product-details-fixed-img">
                                <div class="easyzoom-style">
                                    <div class="easyzoom easyzoom--overlay">
                                        <a href="${pro.imageURL}">
                                            <img src="${pro.imageURL}" alt="">
                                        </a>
                                    </div>
                                    <a class="easyzoom-pop-up img-popup" href="${pro.imageURL}"><i class="icon-size-fullscreen"></i></a>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-6" style="margin-top: 15%">
                            <div class="product-details-content pro-details-content-mrg">
                                <h2>${pro.productName}</h2>

                                <p>${pro.description}</p>
                                <div class="pro-details-price">
                                    <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                        <span class="text-muted old-price">$${pro.unitPrice} </span>
                                    </c:if>
                                    <spam class="new-price"> $${pro.priceAferDiscount}</spam>                               
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
            <div class="description-review-wrapper pb-110"> 
                <div class="container" class="pull-left" style="background-color: #d3d6d8">
                    <div class="row bootstrap snippets bootdeys">
                        <div class="col-md-8 col-sm-12"  >
                            <div class="comment-wrapper">
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h3 style="text-align: center">Comment</h3>
                                    </div>
                                    <div class="panel-body">
                                        <c:choose>
                                            <c:when test="${sessionScope.Account ==null}">
                                                <ul class="media-list">
                                                    <c:forEach items="${list}" var="l">
                                                        <li class="media">
                                                            <a href="#" class="pull-left">
                                                                <img src="${l.imageURL}" alt="" class="img-circle" >
                                                            </a>
                                                            <div class="media-body">
                                                                <span class="text-muted pull-right">
                                                                    <small class="text-muted">${l.timeComment}</small>
                                                                </span>
                                                                <strong class="text-success">${l.disPlayName}</strong>
                                                                <p>
                                                                    ${l.feedbackContent} </a>.
                                                                </p>
                                                            </div>
                                                        </li>   
                                                    </c:forEach>
                                                </ul>
                                            </c:when> <c:otherwise>
                                                <form action="details?do=postcomment&proID=${proID}&accID=${sessionScope.Account.accountid}" method="post">
                                                    <textarea class="form-control" name="comment" placeholder="write a comment..." rows="3" ></textarea>
                                                    <br>
                                                    <button type="submit" name="submit" class="btn btn-info pull-right">Post</button>  
                                                    <div class="clearfix"></div>
                                                    <hr>   
                                                </form>
                                                <ul class="media-list">
                                                    <c:forEach items="${list}" var="l">
                                                        <li class="media">
                                                            <a href="#" class="pull-left">
                                                                <img src="${l.imageURL}" alt="" class="img-circle" height="60px">
                                                            </a>
                                                            <div class="media-body">
                                                                <span class="text-muted pull-right">
                                                                    <small class="text-muted">${l.timeComment}</small>
                                                                </span>
                                                                <strong class="text-success">${l.disPlayName}</strong>
                                                                <p>
                                                                    ${l.feedbackContent} </a>.
                                                                </p>
                                                            </div>
                                                        </li> 
                                                        <br>
                                                    </c:forEach>
                                                </ul>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="related-product pb-115">
                <div class="container">
                    <div class="section-title mb-45 text-center">
                        <h2>Related Product</h2>
                    </div>
                    <div class="related-product-active">
                        <c:forEach items="${listrelated}" var="rel">
                            <div class="product-plr-1">
                                <div class="single-product-wrap">
                                    <div class="product-img product-img-zoom mb-15">
                                        <a href="details.jsp">
                                            <img src="${rel.imageURL}" alt="">
                                        </a>
                                        <div class="product-action-2 tooltip-style-2">
                                        </div>
                                    </div>
                                    <div class="product-content-wrap-2 text-center">
                                        <div class="product-rating-wrap">
                                            <div class="product-rating">
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star gray"></i>
                                            </div>
                                            <span>(2)</span>
                                        </div>
                                        <h3><a href="details?do=details&pid=${rel.productID}">${rel.productName}</a></h3>
                                        <div class="product-price-2">
                                            <span>$${rel.unitPrice}</span>
                                        </div>
                                    </div>
                                    <div class="product-content-wrap-2 product-content-position text-center">
                                        <div class="product-rating-wrap">
                                            <div class="product-rating">
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star"></i>
                                                <i class="icon_star gray"></i>
                                            </div>
                                            <span>(2)</span>
                                        </div>
                                        <h3><a href="details.jsp">${rel.productName}</a></h3>
                                        <div class="product-price-2">
                                            <span>$${rel.unitPrice}</span>
                                        </div>
                                        <div class="pro-add-to-cart">
                                            <button title="Add to Cart"><a href="AddToCart?pid=${rel.productID}" style="color: white">Add To Cart</a></button>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </c:forEach>


                    </div>
                </div>
            </div>

            <%@include file="component/FooterComponent.jsp" %>
            <!-- Modal -->

            <!-- Modal end -->
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