<%@page import="Entity.Account"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <!--  chatbox-->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=yes">
        <title>Chat Bot UI/UX & html for web Plugin | Css3 Transition </title>
        <meta name="description" content="Chat Bot UI/UX & html for web" />
        <meta name="keywords"
              content="Chat Bot UI/UX & html for web, UI/UX for chat bot, chat bot html, best chatbot, chatbot app, online chat bot plugin" />
        <meta name="author" content="css3transition" />
        <link rel="shortcut icon" href="../favicon.ico">
        <meta name="description"
              content="Chat Bot UI/UX & html for web, UI/UX for chat bot, chat bot html, best chatbot, chatbot app, online chat bot plugin | Css3Transition " />
        <meta name="keywords"
              content="Chat Bot UI/UX & html for web, UI/UX for chat bot, chat bot html, best chatbot, chatbot app, online chat bot plugin" />
        <meta name="abstract"
              content="Chat Bot UI/UX & html for web, UI/UX for chat bot, chat bot html, best chatbot, chatbot app, online chat bot plugin">
        <meta name="author" content="Rahul Yaduvanshi">
        <meta name="technologies" content="HTML5, CSS3, HTML, CSS, JQUERY, Bootstrap, Angular">
        <meta name="distribution" content="Global">
        <meta name="development" content="Rahul Yaduvanshi">
        <meta name="robots" content="index, follow">
        <meta name="googlebot" content="index, follow">
        <meta name="city" content="New Delhi">
        <meta name="country" content="india">
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <link href="css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/chatBot.css" rel="stylesheet" type="text/css" />
        <link href="css/timeline.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            div.b {
                word-wrap: break-word;
            }
        </style>
        <!-- end chatbox-->
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Food Order Online</title>
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
        <!-- Chat bot UI start -->
        <c:if test="${sessionScope.Account !=null}">
            <div class="chat-screen">
                <div class="chat-header">
                    <div class="chat-header-title">
                        Let’s chat? - We're online
                    </div>
                </div>
                <div class="chat-mail">
                    <div class="row">
                        <div class="col-md-12 text-center mb-2">
                            <p>Hi 👋! Please fill out the form below to start chatting with the next available agent.</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <input id="textMessage1"  type="text" class="form-control" placeholder="Name">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="Email">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <button onclick="sendMessage()" class="btn btn-primary btn-rounded btn-block">Start Chat</button>
                        </div>
                        <div class="col-md-12">
                            <div class="powered-by">Powered by ADA Phạm</div>   
                        </div>
                    </div>
                </div>
                <div id="croll" class="chat-body hide">
                    <div class="chat-start">Monday, 1:27 PM</div>
                    <%  List<String> list = (List<String>) request.getAttribute("listMess");
                        Account acc = (Account) session.getAttribute("Account");
                         Integer employeeID1 = (Integer)request.getAttribute("employeeID");
                         String employeeID = String.valueOf(employeeID1);
                    %>
                    <%if(list.size()!=1){%>
                        <% for (String elem : list)
                            if (elem.startsWith(String.valueOf(acc.getAccountid()))) {%>
                    <div id="innerdiv" class="chat-bubble me"><%=elem.substring(Integer.toString(acc.getAccountid()).length(), elem.length())%></div>
                    <%} else {%> 

                    <div id="innerdiv" class="chat-bubble you"><%="CSKH"+employeeID+": "+elem.substring(Integer.toString(employeeID1).length(), elem.length()-Integer.toString(acc.getAccountid()).length())%></div>

                    <%}%>
                   <%}%>
                    
                </div>
                <div class="chat-input hide">
                    <input type="text" placeholder="Type a message..." value="${sessionScope.Account.accountid}" id="textMessage">
                    <div class="input-action-icon">
                        <a><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                class="feather feather-paperclip">
                            <path
                                d="M21.44 11.05l-9.19 9.19a6 6 0 0 1-8.49-8.49l9.19-9.19a4 4 0 0 1 5.66 5.66l-9.2 9.19a2 2 0 0 1-2.83-2.83l8.49-8.48">
                            </path>
                            </svg></a>
                        <a onclick="sendMessage()" id="sent"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                                  stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                                  class="feather feather-send">
                            <line x1="22" y1="2" x2="11" y2="13"></line>
                            <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
                            </svg></a>
                    </div>
                </div>
            </div>
            <div class="chat-bot-icon">
                <img src="img/we-are-here.svg" />
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-message-square animate">
                <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-x ">
                <line x1="18" y1="6" x2="6" y2="18"></line>
                <line x1="6" y1="6" x2="18" y2="18"></line>
                </svg>
            </div>
        </c:if>
        <!-- Chat Bot UI Ends -->

        <!-- Time line Html Ends -->
        <!-- BEGIN GLOBAL MANDATORY SCRIPTS -->


        <!--  end chatbox-->
        <div class="main-wrapper">
            <%@include file="component/HeaderComponent.jsp" %>

            <div class="slider-area bg-gray">
                <div class="hero-slider-active-1 hero-slider-pt-1 nav-style-1 dot-style-1">
                    <div class="single-hero-slider single-animation-wrap">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="hero-slider-content-1 hero-slider-content-1-pt-1 slider-animated-1">
                                        <h4 class="animated">New Arrivals</h4>
                                        <h1 class="animated">Leather Simple <br>Backpacks</h1>
                                        <p class="animated">Discover our collection with leather simple backpacks. Less is more never out trend.</p>
                                        <div class="btn-style-1">
                                            <a class="animated btn-1-padding-1" href="menu">Explore Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="hero-slider-img-1 slider-animated-1">
                                        <img class="animated" src="assets/images/slider/slider1.jpg" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="single-hero-slider single-animation-wrap">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="hero-slider-content-1 hero-slider-content-1-pt-1 slider-animated-1">
                                        <h4 class="animated">New Arrivals</h4>
                                        <h1 class="animated">Leather Simple <br>Backpacks</h1>
                                        <p class="animated">Discover our collection with leather simple backpacks. Less is more never out trend.</p>
                                        <div class="btn-style-1">
                                            <a class="animated btn-1-padding-1" href="product-details.html">Explore Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="hero-slider-img-1 slider-animated-1">
                                        <img class="animated" src="assets/images/slider/slider1.jpg" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Description home-->
            <div class="service-area">
                <div class="container">
                    <div class="service-wrap">
                        <div class="row">
                            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                                <div class="single-service-wrap mb-30">
                                    <div class="service-icon">
                                        <i class="icon-cursor"></i>
                                    </div>
                                    <div class="service-content">
                                        <h3>Free Shipping</h3>
                                        <span>Orders over $100</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                                <div class="single-service-wrap mb-30">
                                    <div class="service-icon">
                                        <i class="icon-reload"></i>
                                    </div>
                                    <div class="service-content">
                                        <h3>Free Returns</h3>
                                        <span>Within 30 days</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                                <div class="single-service-wrap mb-30">
                                    <div class="service-icon">
                                        <i class="icon-lock"></i>
                                    </div>
                                    <div class="service-content">
                                        <h3>100% Secure</h3>
                                        <span>Payment Online</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                                <div class="single-service-wrap mb-30">
                                    <div class="service-icon">
                                        <i class="icon-tag"></i>
                                    </div>
                                    <div class="service-content">
                                        <h3>Best Price</h3>
                                        <span>Guaranteed</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Description home 2-->
            <div class="about-us-area pt-85">
                <div class="container">
                    <div class="border-bottom-1 about-content-pb">
                        <div class="row">
                            <div class="col-lg-3 col-md-3">
                                <div class="about-us-logo">
                                    <img src="assets/images/about/logo.png" alt="logo">
                                </div>
                            </div>
                            <div class="col-lg-9 col-md-9">
                                <div class="about-us-content">
                                    <h3>Introduce</h3>
                                    <p>Norda store is a business concept is to offer fashion and quality at the best price. It has since it was founded in 2018 grown into one of the best WooCommerce Fashion Theme. The content of this site is copyright-protected and is the property of David Moye Creative.</p>
                                    <div class="signature">
                                        <h2>Khắc Bảo</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="product-area section-padding-1 pt-115 pb-75">
                <div class="container">
                    <div class="section-title-tab-wrap mb-45">
                        <div class="section-title">
                            <h2>Featured Products</h2>
                        </div>
                        <div class="tab-style-1 nav">
                            <a class="active" href="#product-1" data-toggle="tab">Best Seller</a>
                            <a href="#product-2" data-toggle="tab"> Trending</a>

                            <a href="chat"> chat</a>

                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="tab-content jump">
                        <div id="product-1" class="tab-pane active">
                            <div class="row">
                                <c:forEach items="${listProduct}" var="pro">
                                    <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12">
                                        <div class="single-product-wrap mb-35">
                                            <div class="product-img product-img-zoom mb-20">
                                                <a href="details?do=details&pid=${pro.productID}">
                                                    <img src="${pro.imageURL}" alt="" style="min-height: 300px;">
                                                </a>
                                                <c:if test="${pro.discount !=0}">
                                                    <span class="pro-badge left bg-red">Sale ${pro.discount*100}%</span>
                                                </c:if>
                                                <div class="product-action-wrap">
                                                    <div class="product-action-left">
                                                        <button><i></i><a href="AddToCart?pid=${pro.productID}">Add To Cart</a></button>
                                                    </div>
                                                    <div class="product-action-right tooltip-style">
                                                        <button data-toggle="modal" data-target="#exampleModal${pro.productID}"><i class="icon-size-fullscreen icons"></i><span>Quick View</span></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="product-content-wrap">
                                                <div class="product-content-left">
                                                    <h4><a href="details?do=details&pid=${pro.productID}">${pro.productName}</a></h4>
                                                    <div class="pro-list-price product-price-2">
                                                        <c:if test="${pro.unitPrice !=pro.priceAferDiscount}">
                                                            <span class="text-muted old-price">$${pro.unitPrice}</span>
                                                        </c:if>
                                                        <spam class="new-price">$${pro.priceAferDiscount}</spam>
                                                    </div>
                                                </div>
                                                <div class="product-content-right tooltip-style">
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
                                                                    <img src="${pro.imageURL}" alt="">
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
                                </c:forEach>
                            </div>
                        </div>
                        <div id="product-2" class="tab-pane">
                            <div class="row">
                                <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12">
                                    <div class="single-product-wrap mb-35">
                                        <div class="product-img product-img-zoom mb-20">
                                            <a href="product-details.html">
                                                <img src="assets/images/product/product-3.jpg" alt="">
                                            </a>
                                            <span class="pro-badge left bg-red">-20%</span>
                                            <div class="product-action-wrap">
                                                <div class="product-action-left">
                                                    <button><i class="icon-basket-loaded"></i>Add to Cart</button>
                                                </div>
                                                <div class="product-action-right tooltip-style">
                                                    <button data-toggle="modal" data-target="#exampleModal"><i class="icon-size-fullscreen icons"></i><span>Quick View</span></button>
                                                    <button class="font-inc"><i class="icon-refresh"></i><span>Compare</span></button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="product-content-wrap">
                                            <div class="product-content-left">
                                                <h4><a href="product-details.html">Lined Brown Swearshirt</a></h4>
                                                <div class="product-price">
                                                    <span class="new-price">$46.00</span>
                                                    <span class="old-price">$66.75</span>
                                                </div>
                                            </div>
                                            <div class="product-content-right tooltip-style">
                                                <button class="font-inc"><i class="icon-heart"></i><span>Wishlist</span></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banner-area pb-85">
                <div class="container">
                    <div class="section-title mb-45">
                        <h2>Our Collections</h2>
                    </div>
                    <div class="row">
                        <div class="col-lg-7 col-md-7">
                            <div class="banner-wrap banner-mr-1 mb-30">
                                <div class="banner-img banner-img-zoom">
                                    <a href="product-details.html"><img src="assets/images/banner/banner1.jpg" alt=""></a>
                                </div>
                                <div class="banner-content-1">
                                    <h2>Zara Pattern Boxed <br>Underwear</h2>
                                    <p>Stretch, fresh-cool help you alway comfortable</p>
                                    <div class="btn-style-1">
                                        <a class="animated btn-1-padding-2" href="menu">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-5">
                            <div class="banner-wrap  banner-ml-1 mb-30">
                                <div class="banner-img banner-img-zoom">
                                    <a href="product-details.html"><img src="assets/images/banner/banner2.jpg" alt=""></a>
                                </div>
                                <div class="banner-content-2">
                                    <h2>Basic Color Caps</h2>
                                    <p>Minimalist never cool, choose and make the simple great again!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="brand-logo-area pt-100 pb-100">
                <div class="container">
                    <div class="brand-logo-wrap brand-logo-mrg">
                        <div class="single-brand-logo mb-10">
                            <img src="assets/images/brand-logo/brand-logo-1.png" alt="brand-logo">
                        </div>
                        <div class="single-brand-logo mb-10">
                            <img src="assets/images/brand-logo/brand-logo-2.png" alt="brand-logo">
                        </div>
                        <div class="single-brand-logo mb-10">
                            <img src="assets/images/brand-logo/brand-logo-3.png" alt="brand-logo">
                        </div>
                        <div class="single-brand-logo mb-10">
                            <img src="assets/images/brand-logo/brand-logo-4.png" alt="brand-logo">
                        </div>
                        <div class="single-brand-logo mb-10">
                            <img src="assets/images/brand-logo/brand-logo-5.png" alt="brand-logo">
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="component/FooterComponent.jsp" %>
            <!-- Modal -->

            <!-- Modal end -->
        </div>

        <!-- All JS is here
    ============================================ -->
        <!-- Chat  -->
        <c:if test="${sessionScope.Account !=null}">
        <script type="text/javascript">
            var websocket = new WebSocket("ws://localhost:8080/SWP391_SE1615_NET_GROUP_2-NEVERGIVEUP/chatRoomServer");
            websocket.onopen = function (message) {
                processOpen(message);
            };
            websocket.onmessage = function (message) {
                processMessage(message);
            };
            websocket.onclose = function (message) {
                processClose(message);
            };
            websocket.onerror = function (message) {
                processError(message);
            };

            function processOpen(message) {
//                $(document).ready(function () {
//                    $('#croll').append('<div id="innerdiv" class="chat-bubble me">Hi there!</div>');
//                });
            }
            function processMessage(message) {
                console.log(message);

                if (message.data.startsWith("${sessionScope.Account.accountid}") && ${employeeID}) {
                    var mess = message.data.replace("${sessionScope.Account.accountid}", "");
                    $('#croll').append('<div id="innerdiv" class="chat-bubble me">' + mess + '</div>');

                }
                if (message.data.startsWith("${employeeID}")&& ${sessionScope.Account.accountid}){
                    var mess = message.data.replace("${employeeID}", "CSKH${employeeID}: ");
                    $('#croll').append('<div id="innerdiv" class="chat-bubble you">' + mess + '</div>');

                }

                //textAreaMessage.value += message.data + " \n";
            }
            function processClose(message) {
                textAreaMessage.value += "Server Disconnect... \n";
            }
            function processError(message) {
                textAreaMessage.value += "Error... " + message + " \n";
            }


            function sendMessage() {
                if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                    var mess = "${sessionScope.Account.accountid}" +"~"+ textMessage.value;
                    if (!isNaN(textMessage.value)) {
                        websocket.send(textMessage.value);
                    } else {
                        websocket.send(mess);
                    }
                }
                textMessage.value = "";
            }

        </script>
        <script type="257be86a981729866f2fa61c-text/javascript">
            (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
            })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

            ga('create', 'UA-83834093-1', 'auto');
            ga('send', 'pageview');

        </script>
        <script src="https://ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js"
        data-cf-settings="257be86a981729866f2fa61c-|49" defer=""></script>
        <!-- Time line Html Ends -->
        <!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <!--<script src="js/bootstrap.min.js"></script>-->
        <script src="js/select2.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".select2_el").select2({
                });
            });
        </script>


        <script>
            $(document).ready(function () {
                $("#show-ba-cham").click(function () {
                    $("#te").show();
                });
                $("#sent").click(function () {
                    $("#te").hide();
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                //Toggle fullscreen
                $(".chat-bot-icon").click(function (e) {
                    $(this).children('img').toggleClass('hide');
                    $(this).children('svg').toggleClass('animate');
                    $('.chat-screen').toggleClass('show-chat');
                });

                $('.chat-mail button').click(function () {
                    $('.chat-mail').addClass('hide');
                    $('.chat-body').removeClass('hide');
                    $('.chat-input').removeClass('hide');
                    $('.chat-header-option').removeClass('hide');
                });
                $('.end-chat').click(function () {
                    $('.chat-body').addClass('hide');
                    $('.chat-input').addClass('hide');
                    $('.chat-session-end').removeClass('hide');
                    $('.chat-header-option').addClass('hide');
                });
            }
            );

        </script>
        </c:if>
        <!-- chat end -->
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


        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

    </body>

</html>
