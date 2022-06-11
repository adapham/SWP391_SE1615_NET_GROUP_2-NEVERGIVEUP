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

        <!-- Use the minified version files listed below for better performance and remove the files listed above
        <link rel="stylesheet" href="assets/css/vendor/vendor.min.css">
        <link rel="stylesheet" href="assets/css/plugins/plugins.min.css">
        <link rel="stylesheet" href="assets/css/style.min.css"> -->

    </head>

    <body>

        <div class="main-wrapper">
            <%@include file="component/HeaderComponent.jsp" %>
            <!-- Mobile menu start -->

            <!-- mini cart start -->

            <div class="breadcrumb-area bg-gray">
                <div class="container">
                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="index.html">Home</a>
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
                                <table>
                                    <thead>
                                        <tr>                                                
                                            <th>ProductID</th>
                                            <th>Image</th>
                                            <th>ProductName</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>                                
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listProductCarts}" var="pro">
                                        <form method="post" action="updateQuantity">
                                            <tr>
                                            <input hidden="" value="${pro.productID}" name="pid"/>
                                            <td>${pro.productID}</td>
                                            <td class="product-thumbnail">
                                                <img src="${pro.imageURL}" alt="" style="height: 150px; width: 100%">
                                            </td>
                                            <td class="product-name">${pro.productName}</td>
                                            <td class="product-price-cart"><span class="amount">$${pro.unitPrice}</span></td>                                                       
                                            <td>${pro.quantity}</td>   
                                            <td class="product-subtotal">$${Math.round((pro.unitPrice * pro.quantity)*100)/100}</td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="cart-shiping-update-wrapper">
                                        <div class="cart-shiping-update">
                                            <a href="shop.jsp">Continue Shopping</a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-lg-6 col-md-6">
                                    <div class="cart-tax">
                                        <div class="title-wrap">
                                            <h4 class="cart-bottom-title section-bg-gray">Information of Customer</h4>
                                        </div>
                                        <div class="tax-wrapper">
                                            <p>Enter your information</p>
                                            <form method="post" action="checkOut">
                                                <div class="tax-select-wrapper">

                                                    <div class="tax-select">
                                                        <label>
                                                            * Account ID
                                                        </label>
                                                        <input type="text" maxlength="50" value="${account.accountid}" name="accountid" readonly="">
                                                    </div>
                                                    <div class="tax-select">
                                                        <label>
                                                            * Email
                                                        </label>
                                                        <input type="text" maxlength="50" value="${account.email}" name="email">
                                                    </div>
                                                    <div class="tax-select">
                                                        <label>
                                                            * Address
                                                        </label>
                                                        <input type="text" maxlength="50" value="${account.address}" name="address">
                                                    </div>
                                                    <div class="tax-select">
                                                        <label>
                                                            * Phone
                                                        </label>
                                                        <input type="text" maxlength="15" value="${account.phone}" name="phone">
                                                    </div>   

                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-12">
                                    <div class="grand-totall">
                                        <div class="title-wrap">
                                            <h4 class="cart-bottom-title section-bg-gary-cart">Cart Total</h4>
                                        </div>
                                        <h5>Total Amount<span>$${totalMoney}</span></h5>

                                        
                                            
                                            <button type="botton" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                                <i style="font-style: normal">Proceed to Checkout</i>
                                            </button>

                                            <!-- The Modal -->
                                            <div class="modal" id="myModal">
                                                <div class="modal-dialog">
                                                    <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Order success</h4>
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <!-- Modal body -->
                                                        <div class="modal-body">
                                                            Thank you for your order.
                                                        </div>
                                                        <!-- Modal footer -->
                                                        <form method="post" action="checkOut?accountID=${account.accountid}&address=${account.address}&email=${account.email}&phone=${account.phone}">
                                                        <div class="modal-footer">
                                                            <button value="1" name="temp" type="submit" class="btn btn-danger">Continue shopping</button>
                                                            <button value="2" name="temp" type="submit" class="btn btn-danger">Logout</button>
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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <!-- Use the minified version files listed below for better performance and remove the files listed above  
    <script src="assets/js/vendor/vendor.min.js"></script>
    <script src="assets/js/plugins/plugins.min.js"></script>  -->
        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

    </body>

</html>