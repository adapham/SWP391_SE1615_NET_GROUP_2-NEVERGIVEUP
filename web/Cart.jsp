<%-- 
    Document   : about
    Created on : May 28, 2022, 2:06:13 PM
    Author     : KhacBao
--%>
<%@page import="View.Product"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/main.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5 d-flex justify-content-center">
                <a class="navbar-brand" href="home?do=home">Foodie</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home?do=blogList">Blog List</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=home">Menu</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="home?do=contact">Contact</a></li>
                    </ul>                  
                </div>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item dropdown">
                        <c:if test="${sessionScope.Account ==null}">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false"> <img src="https://cdn.iconscout.com/icon/free/png-256/account-avatar-profile-human-man-user-30448.png" height="40px" width="70%"/>  
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.Account !=null}">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false"> <img src="${sessionScope.Account.imageURL}" class="rounded-circle" alt="A girl" width="50"/>  
                            </a> ${sessionScope.Account.displayname}  
                        </c:if>
                        <c:if test="${sessionScope.Account ==null}">
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="login">Login</a></li>
                                <li><a class="dropdown-item" href="login?do=Register">Regsiter</a></li>  
                            </ul>   
                        </c:if>
                        <c:if test="${sessionScope.Account !=null}">
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="login?do=logout">Logout</a></li>
                                <li><a class="dropdown-item" href="login?do=updateprofile">Update Profile</a></li>  
                            </ul>   
                        </c:if>

                    </li>
                </ul>
            </div>
        </nav>
        <main class="page">
            <section class="shopping-cart dark" style="min-height: 435px">
                <div class="container">

                    <div class="block-heading">
                        <h2>List Foods</h2>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ProductID</th>
                                <th scope="col">Image</th>
                                <th scope="col">ProductName</th>
                                <th scope="col">Quantity</th> 
                                <th scope="col">Price</th>
                                <th scope="col">Total</th>
                                <th scope="col">Remove</th>
                            </tr>
                        </thead>
                        <tbody>



                            <c:forEach items="${listProductCarts}" var="l">
                            <form method="post" action="updateQuantity">
                                <tr>
                                <input hidden="" value="${l.productID}" name="pid"/>
                                <td scope="row">${l.productID}</td>
                                <td><img src="${l.imageURL}"></td>
                                <td>${l.productName}</td>
                                <td><input onchange="this.form.submit()" type="number" value="${l.quantity}" name="quantity"></td>
                                <td>${l.unitPrice}</td>
                                <td>${Math.round((l.unitPrice * l.quantity)*100)/100}</td>
                                <td><a href="delete?pid=${l.productID}" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Detele</a></td>    
                                </tr>
                            </form>
                        </c:forEach>


                        </tbody>                   
                        <td colspan="5" ><h3>Total Amount:$${totalMoney}</h3></td>
                        <td colspan="5" ><a href="delete" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Delete All</a></td>  

                    </table> 
                    <c:if test="${sessionScope.Account != null}">
                        <a href="checkOut" class="btn btn-success w-15">Check Out</a>
                    </c:if>
                    <c:if test="${sessionScope.Account == null}">
                        <a href="login" class="btn btn-success w-15">Check Out</a>
                    </c:if>
                </div>
            </section>
        </main>                
        <!-- Footer-->
        <%@include file="component/FooterHome.jsp" %>
    </body>
</html>


