<header class="header-area transparent-bar section-padding-1">
    <div class="container-fluid">
        <div class="header-large-device">
            <div class="header-bottom">
                <div class="row align-items-center">
                    <div class="col-xl-2 col-lg-2">
                        <div class="logo">
                            <a href="home"><img src="assets/images/logo/logo.png" alt="logo"></a>
                        </div>
                    </div>
                    <div class="col-xl-8 col-lg-7">
                        <div class="main-menu main-menu-padding-1 main-menu-lh-1">
                            <nav>
                                <ul>
                                    <li><a href="home">HOME </a>
                                    </li>
                                    <li><a href="menu">SHOP </a>
                                    </li>
                                    <li><a href="#">PAGES </a>
                                        <ul class="sub-menu-style">
                                            <li><a href="home?do=about">about us </a></li>
                                            <li><a href="cart.jsp">cart page</a></li>
                                            <li><a href="checkout.jsp">checkout </a></li>
                                            <li><a href="my-account.html">my account</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="blog-no-sidebar.html">BLOG </a>
                                    </li>
                                    <li><a href="contact.html">CONTACT </a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-3">
                        <div class="header-action header-action-flex header-action-mrg-right">
                            <div class="same-style-2 header-search-1">
                                <a class="search-toggle" href="#">
                                    <i class="icon-magnifier s-open"></i>
                                    <i class="icon_close s-close"></i>
                                </a>
                                <div class="search-wrap-1">
                                    <form action="#">
                                        <input placeholder="Search products?" type="text">
                                        <button class="button-search"><i class="icon-magnifier"></i></button>
                                    </form>
                                </div>
                            </div>
                            <div class="same-style-2">
                                <a href="login-register.html"><i class="icon-user"></i></a>
                            </div>
                            <div class="same-style-2">
                                <a href="cart">
                                    <c:if test="${sessionScope.size==null}">
                                        <i class="icon-basket-loaded"></i><span class="pro-count red">0</span>
                                    </c:if>
                                    <c:if test="${sessionScope.size!=null}">
                                        <i class="icon-basket-loaded"></i><span class="pro-count red">${sessionScope.size}</span>
                                    </c:if>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>