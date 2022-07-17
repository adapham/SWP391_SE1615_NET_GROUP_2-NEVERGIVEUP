<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                    <li><a href="home?do=home&fresh=1">HOME </a>
                                    </li>
                                    <c:if test="${sessionScope.Account !=null}">
                                                <li><a href="MyCartController">MY CART</a> 
                                                </li>
                                    </c:if>
                                    <li><a href="menu">SHOP </a>
                                    </li>
                                    <li><a href="#">PAGES </a>
                                        <ul class="sub-menu-style">
                                            <li><a href="home?do=about">about us </a></li>
                                            <li><a href="cart">cart page</a></li>
                                            <li><a href="login">checkout </a></li>
                                            <c:if test="${sessionScope.Account !=null}">
                                                <li><a href="login?do=updateprofile">my account</a></li>
                                            </c:if>
                                        </ul>
                                    </li>
                                    <li><a href="home?do=blog">BLOG </a>
                                    </li>
                                    <li><a href="home?do=contact">CONTACT </a></li>
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
                                <a href="cart">
                                    <c:if test="${sessionScope.size==null}">
                                        <i class="icon-basket-loaded"></i><span class="pro-count red">0</span>
                                    </c:if>
                                    <c:if test="${sessionScope.size!=null}">
                                        <i class="icon-basket-loaded"></i><span class="pro-count red">${sessionScope.size}</span>
                                    </c:if>
                                </a>
                            </div>
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