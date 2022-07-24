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
            <%@include file="component/HeaderComponent.jsp" %>
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
                    <h3 class="cart-page-title">Your cart items</h3>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="table-content table-responsive cart-table-content">
                                <table class="border table table-striped table-hover table-bordered border-primary" style="margin-top: 10px; color: black">
                                    <thead>
                                        <tr>
                                            <th>Product ID</th>
                                            <th>Image</th>
                                            <th style="text-transform: inherit">Product name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listProductCarts}" var="pro">
                                        <form method="post" action="updateQuantity">
                                            <tr>
                                            <input hidden="" value="${pro.productID}" name="pid"/>
                                            <td style="text-align: right">${pro.productID}</td>
                                            <td class="product-thumbnail">
                                                <img src="${pro.imageURL}" alt="" style="height: 150px; width: 100%">
                                            </td>
                                            <td class="product-name">${pro.productName}</td>
                                            <td class="product-price-cart" style="text-align: right"><span class="amount">$${pro.unitPrice}</span></td>                                                       
                                            <td><input style="text-align: right; width: 50%" onchange="this.form.submit()" type="number" value="${pro.quantity}" min="1" name="quantity" oninput="this.value = Math.abs(this.value)"></td>
                                            <td style="text-align: right" class="product-subtotal">$${Math.round((pro.unitPrice * pro.quantity)*100)/100}</td>
                                            <td class="product-remove">                                                
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${pro.productID}">
                                                    <i class="icon_close"></i>
                                                </button>

                                                <!-- The Modal -->
                                                <div class="modal" id="myModal${pro.productID}">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                            <!-- Modal Header -->
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Delete product</h4>
                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            </div>
                                                            <!-- Modal body -->
                                                            <div class="modal-body">
                                                                Are you sure you want to delete the product?
                                                            </div>
                                                            <!-- Modal footer -->
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                                <button type="button" class="btn btn-danger"><a href="delete?pid=${pro.productID}">Confirm</a></button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                    <td colspan="2" ><h3>Total Amount: $${totalMoney}</h3></td>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="cart-shiping-update-wrapper">
                                        <div class="cart-shiping-update">
                                            <a href="menu">Continue Shopping</a>
                                        </div>
                                        <div class="cart-clear">
                                            <c:if test="${sessionScope.size!=0 && sessionScope.size!=null}">
                                                <c:if test="${sessionScope.Account !=null}">
                                                    <a href="checkOut">Check Out</a>
                                                </c:if>
                                                <c:if test="${sessionScope.Account ==null}">
                                                    <a href="login">Check Out</a>
                                                </c:if>
                                            </c:if>


                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalall">
                                                <a style="color: black">Clear Cart</a>
                                            </button>

                                            <!-- The Modal -->
                                            <div class="modal" id="myModalall">
                                                <div class="modal-dialog">
                                                    <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Delete all products</h4>
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <!-- Modal body -->
                                                        <div class="modal-body">
                                                            Are you sure you want to delete all of the product?
                                                        </div>
                                                        <!-- Modal footer -->
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                            <button type="button" class="btn btn-danger" ><a href="delete">Confirm</a></button>
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
        <!--        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->

        <!-- Use the minified version files listed below for better performance and remove the files listed above  
    <script src="assets/js/vendor/vendor.min.js"></script>
    <script src="assets/js/plugins/plugins.min.js"></script>  -->
        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

    </body>

</html>