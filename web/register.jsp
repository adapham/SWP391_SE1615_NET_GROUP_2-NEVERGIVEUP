<!doctype html>
<html class="no-js" lang="en">
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Norda - Minimal eCommerce HTML Template</title>
        <meta name="robots" content="noindex, follow" />
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png">
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
            <!-- mini cart start -->

            <div class="breadcrumb-area bg-gray">
                <div class="container">
                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="index.jsp">Home</a>
                            </li>
                            <li class="active">register</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="login-register-area pt-115 pb-120">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                            <div class="login-register-wrapper">
                                <div class="login-register-tab-list nav">
                                    <a class="active" data-toggle="tab">
                                        <h4> Register </h4>
                                    </a>       
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane"></div>
                                    <div class="login-form-container">
                                        <c:if test="${mess != null}"> <h5 style="color: red;text-align: center">${mess}</h5></c:if>
                                            <div class="login-register-form">
                                                <form action="login?do=Register" method="post">
                                                <c:choose>
                                                    <c:when test="${username != null}">
                                                        <input maxlength="50" type="text" class="form-control" placeholder="User Name *" value="${username}" name="username" />                                     
                                                    </c:when> <c:otherwise>
                                                        <input maxlength="50" type="text" class="form-control" placeholder="User Name *" value="" name="username" />      
                                                    </c:otherwise>                                       
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${password != null}">                                        
                                                        <input maxlength="50" type="password" class="form-control" placeholder="Password *" value="${password}" name="password" />
                                                    </c:when><c:otherwise>
                                                        <input maxlength="50" type="password" class="form-control" placeholder="Password *" value="" name="password" />      
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${displayname != null}">                                    
                                                        <input maxlength="50" type="text" class="form-control" placeholder="DisplayName *" value="${displayname}" name="displayname" />
                                                    </c:when><c:otherwise>
                                                        <input maxlength="50" type="text" class="form-control" placeholder="DisplayName *" value="" name="displayname" />    
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${address != null}">
                                                        <input maxlength="50" type="text" class="form-control" placeholder="Address " value="${address}" name="address" /> 
                                                    </c:when><c:otherwise>
                                                        <input maxlength="50" type="text" class="form-control" placeholder="Address " value="" name="address" />   
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${email != null}">                                    
                                                        <input maxlength="50" type="email" class="form-control" placeholder="Email *" value="${email}" name="email" />
                                                    </c:when><c:otherwise>
                                                        <input maxlength="50" type="email" class="form-control" placeholder="Email *" value="" name="email" />     
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${phone != null}">
                                                        <input maxlength="10" type="phone" class="form-control" placeholder="Phone *" value="${phone}" name="phone" />
                                                    </c:when><c:otherwise>
                                                        <input maxlength="10" type="phone" class="form-control" placeholder="Phone *" value="" name="phone" /> 
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${imageURL != null}">
                                                        <input type="text" class="form-control" placeholder="ImageURL " value="${imageURL}" name="imageURL" />
                                                    </c:when><c:otherwise>
                                                        <input type="text" class="form-control" placeholder="ImageURL " value="" name="imageURL" />
                                                    </c:otherwise>
                                                </c:choose>
                                                
                                                <div class="button-box">
                                                    <button name="submit" type="submit">Register</button>
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
        <div class="subscribe-area bg-gray pt-115 pb-115">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 col-md-5">
                        <div class="section-title">
                            <h2>keep connected</h2>
                            <p>Get updates by subscribe our weekly newsletter</p>
                        </div>
                    </div>
                    <div class="col-lg-7 col-md-7">
                        <div id="mc_embed_signup" class="subscribe-form">
                            <form id="mc-embedded-subscribe-form" class="validate subscribe-form-style" novalidate="" target="_blank" name="mc-embedded-subscribe-form" method="post" action="http://devitems.us11.list-manage.com/subscribe/post?u=6bbb9b6f5827bd842d9640c82&amp;id=05d85f18ef">
                                <div id="mc_embed_signup_scroll" class="mc-form">
                                    <input class="email" type="email" required="" placeholder="Enter your email address" name="EMAIL" value="">
                                    <div class="mc-news" aria-hidden="true">
                                        <input type="text" value="" tabindex="-1" name="b_6bbb9b6f5827bd842d9640c82_05d85f18ef">
                                    </div>
                                    <div class="clear">
                                        <input id="mc-embedded-subscribe" class="button" type="submit" name="subscribe" value="Subscribe">
                                    </div>
                                </div>
                            </form>
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