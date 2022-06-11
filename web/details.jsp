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
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="dec-review-topbar nav mb-45">
                                <a data-toggle="tab" href="#des-details4">Reviews and Ratting </a>
                            </div>
                            <div class="tab-content dec-review-bottom">
                                <div id="des-details4" class="tab-pane">
                                    <div class="review-wrapper">
                                        <h2>1 review for Sleeve Button Cowl Neck</h2>
                                        <div class="single-review">
                                            <div class="review-img">
                                                <img src="assets/images/product-details/client-1.png" alt="">
                                            </div>
                                            <div class="review-content">
                                                <div class="review-top-wrap">
                                                    <div class="review-name">
                                                        <h5><span>John Snow</span> - March 14, 2019</h5>
                                                    </div>
                                                    <div class="review-rating">
                                                        <i class="yellow icon_star"></i>
                                                        <i class="yellow icon_star"></i>
                                                        <i class="yellow icon_star"></i>
                                                        <i class="yellow icon_star"></i>
                                                        <i class="yellow icon_star"></i>
                                                    </div>
                                                </div>
                                                <p>Donec accumsan auctor iaculis. Sed suscipit arcu ligula, at egestas magna molestie a. Proin ac ex maximus, ultrices justo eget, sodales orci. Aliquam egestas libero ac turpis pharetra, in vehicula lacus scelerisque</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="ratting-form-wrapper">

                                        <div class="ratting-form">
                                            <form action="#">
                                                <div class="row">

                                                    <div class="col-lg-12">

                                                        <div class="col-md-12">
                                                            <div class="rating-form-style mb-20">
                                                                <label>Your review <span>*</span></label>
                                                                <textarea name="Your Review"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="form-submit">
                                                                <input type="submit" value="Submit">
                                                            </div>
                                                        </div>
                                                    </div>
                                            </form>
                                        </div>
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
                        <div class="product-plr-1">
                            <div class="single-product-wrap">
                                <div class="product-img product-img-zoom mb-15">
                                    <a href="product-details.html">
                                        <img src="assets/images/product/product-13.jpg" alt="">
                                    </a>
                                    <div class="product-action-2 tooltip-style-2">
                                        <button title="Wishlist"><i class="icon-heart"></i></button>
                                        <button title="Quick View" data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i></button>
                                        <button title="Compare"><i class="icon-refresh"></i></button>
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
                                    <h3><a href="product-details.html">Basic Joggin Shorts</a></h3>
                                    <div class="product-price-2">
                                        <span>$20.50</span>
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
                                    <h3><a href="product-details.html">Basic Joggin Shorts</a></h3>
                                    <div class="product-price-2">
                                        <span>$20.50</span>
                                    </div>
                                    <div class="pro-add-to-cart">
                                        <button title="Add to Cart">Add To Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="product-plr-1">
                            <div class="single-product-wrap">
                                <div class="product-img product-img-zoom mb-15">
                                    <a href="product-details.html">
                                        <img src="assets/images/product/product-14.jpg" alt="">
                                    </a>
                                    <span class="pro-badge left bg-red">-20%</span>
                                    <div class="product-action-2 tooltip-style-2">
                                        <button title="Wishlist"><i class="icon-heart"></i></button>
                                        <button title="Quick View" data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i></button>
                                        <button title="Compare"><i class="icon-refresh"></i></button>
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
                                    <h3><a href="product-details.html">Make Thing Happen T-Shirt</a></h3>
                                    <div class="product-price-2">
                                        <span class="new-price">$35.45</span>
                                        <span class="old-price">$45.80</span>
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
                                    <h3><a href="product-details.html">Make Thing Happen T-Shirt</a></h3>
                                    <div class="product-price-2">
                                        <span class="new-price">$35.45</span>
                                        <span class="old-price">$45.80</span>
                                    </div>
                                    <div class="pro-add-to-cart">
                                        <button title="Add to Cart">Add To Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="product-plr-1">
                            <div class="single-product-wrap">
                                <div class="product-img product-img-zoom mb-15">
                                    <a href="product-details.html">
                                        <img src="assets/images/product/product-15.jpg" alt="">
                                    </a>
                                    <div class="product-action-2 tooltip-style-2">
                                        <button title="Wishlist"><i class="icon-heart"></i></button>
                                        <button title="Quick View" data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i></button>
                                        <button title="Compare"><i class="icon-refresh"></i></button>
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
                                    <h3><a href="product-details.html">Basic White Simple Sneaker</a></h3>
                                    <div class="product-price-2">
                                        <span>$35.45</span>
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
                                    <h3><a href="product-details.html">Basic White Simple Sneaker</a></h3>
                                    <div class="product-price-2">
                                        <span>$35.45</span>
                                    </div>
                                    <div class="pro-add-to-cart">
                                        <button title="Add to Cart">Add To Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="product-plr-1">
                            <div class="single-product-wrap">
                                <div class="product-img product-img-zoom mb-15">
                                    <a href="product-details.html">
                                        <img src="assets/images/product/product-18.jpg" alt="">
                                    </a>
                                    <div class="product-action-2 tooltip-style-2">
                                        <button title="Wishlist"><i class="icon-heart"></i></button>
                                        <button title="Quick View" data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i></button>
                                        <button title="Compare"><i class="icon-refresh"></i></button>
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
                                    <h3><a href="product-details.html">Tie-up Sute Sandals</a></h3>
                                    <div class="product-price-2">
                                        <span>$55.50</span>
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
                                    <h3><a href="product-details.html">Tie-up Sute Sandals</a></h3>
                                    <div class="product-price-2">
                                        <span>$55.50</span>
                                    </div>
                                    <div class="pro-add-to-cart">
                                        <button title="Add to Cart">Add To Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="product-plr-1">
                            <div class="single-product-wrap">
                                <div class="product-img product-img-zoom mb-15">
                                    <a href="product-details.html">
                                        <img src="assets/images/product/product-19.jpg" alt="">
                                    </a>
                                    <div class="product-action-2 tooltip-style-2">
                                        <button title="Wishlist"><i class="icon-heart"></i></button>
                                        <button title="Quick View" data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i></button>
                                        <button title="Compare"><i class="icon-refresh"></i></button>
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
                                    <h3><a href="product-details.html">Faded Grey T-Shirt</a></h3>
                                    <div class="product-price-2">
                                        <span>$65.50</span>
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
                                    <h3><a href="product-details.html">Faded Grey T-Shirt</a></h3>
                                    <div class="product-price-2">
                                        <span>$65.50</span>
                                    </div>
                                    <div class="pro-add-to-cart">
                                        <button title="Add to Cart">Add To Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="component/FooterComponent.jsp" %>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
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
                                            <img src="assets/images/product/product-1.jpg" alt="">
                                        </div>
                                        <div id="pro-2" class="tab-pane fade">
                                            <img src="assets/images/product/product-3.jpg" alt="">
                                        </div>
                                        <div id="pro-3" class="tab-pane fade">
                                            <img src="assets/images/product/product-6.jpg" alt="">
                                        </div>
                                        <div id="pro-4" class="tab-pane fade">
                                            <img src="assets/images/product/product-3.jpg" alt="">
                                        </div>
                                    </div>
                                    <div class="quickview-wrap mt-15">
                                        <div class="quickview-slide-active nav-style-6">
                                            <a class="active" data-toggle="tab" href="#pro-1"><img src="assets/images/product/quickview-s1.jpg" alt=""></a>
                                            <a data-toggle="tab" href="#pro-2"><img src="assets/images/product/quickview-s2.jpg" alt=""></a>
                                            <a data-toggle="tab" href="#pro-3"><img src="assets/images/product/quickview-s3.jpg" alt=""></a>
                                            <a data-toggle="tab" href="#pro-4"><img src="assets/images/product/quickview-s2.jpg" alt=""></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-7 col-md-6 col-12 col-sm-12">
                                    <div class="product-details-content quickview-content">
                                        <h2>Simple Black T-Shirt</h2>
                                        <div class="product-ratting-review-wrap">
                                            <div class="product-ratting-digit-wrap">
                                                <div class="product-ratting">
                                                    <i class="icon_star"></i>
                                                    <i class="icon_star"></i>
                                                    <i class="icon_star"></i>
                                                    <i class="icon_star"></i>
                                                    <i class="icon_star"></i>
                                                </div>
                                                <div class="product-digit">
                                                    <span>5.0</span>
                                                </div>
                                            </div>
                                            <div class="product-review-order">
                                                <span>62 Reviews</span>
                                                <span>242 orders</span>
                                            </div>
                                        </div>
                                        <p>Seamlessly predominate enterprise metrics without performance based process improvements.</p>
                                        <div class="pro-details-price">
                                            <span class="new-price">$75.72</span>
                                            <span class="old-price">$95.72</span>
                                        </div>
                                        <div class="pro-details-color-wrap">
                                            <span>Color:</span>
                                            <div class="pro-details-color-content">
                                                <ul>
                                                    <li><a class="dolly" href="#">dolly</a></li>
                                                    <li><a class="white" href="#">white</a></li>
                                                    <li><a class="azalea" href="#">azalea</a></li>
                                                    <li><a class="peach-orange" href="#">Orange</a></li>
                                                    <li><a class="mona-lisa active" href="#">lisa</a></li>
                                                    <li><a class="cupid" href="#">cupid</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="pro-details-size">
                                            <span>Size:</span>
                                            <div class="pro-details-size-content">
                                                <ul>
                                                    <li><a href="#">XS</a></li>
                                                    <li><a href="#">S</a></li>
                                                    <li><a href="#">M</a></li>
                                                    <li><a href="#">L</a></li>
                                                    <li><a href="#">XL</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="pro-details-quality">
                                            <span>Quantity:</span>
                                            <div class="cart-plus-minus">
                                                <input class="cart-plus-minus-box" type="text" name="qtybutton" value="1">
                                            </div>
                                        </div>
                                        <div class="product-details-meta">
                                            <ul>
                                                <li><span>Categories:</span> <a href="#">Woman,</a> <a href="#">Dress,</a> <a href="#">T-Shirt</a></li>
                                                <li><span>Tag: </span> <a href="#">Fashion,</a> <a href="#">Mentone</a> , <a href="#">Texas</a></li>
                                            </ul>
                                        </div>
                                        <div class="pro-details-action-wrap">
                                            <div class="pro-details-add-to-cart">
                                                <a title="Add to Cart" href="#">Add To Cart </a>
                                            </div>
                                            <div class="pro-details-action">
                                                <a title="Add to Wishlist" href="#"><i class="icon-heart"></i></a>
                                                <a title="Add to Compare" href="#"><i class="icon-refresh"></i></a>
                                                <a class="social" title="Social" href="#"><i class="icon-share"></i></a>
                                                <div class="product-dec-social">
                                                    <a class="facebook" title="Facebook" href="#"><i class="icon-social-facebook"></i></a>
                                                    <a class="twitter" title="Twitter" href="#"><i class="icon-social-twitter"></i></a>
                                                    <a class="instagram" title="Instagram" href="#"><i class="icon-social-instagram"></i></a>
                                                    <a class="pinterest" title="Pinterest" href="#"><i class="icon-social-pinterest"></i></a>
                                                </div>
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