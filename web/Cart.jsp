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
        <%@include file="component/NavbarHome.jsp" %>
        <!--Section-->
        <main class="page">
            <section class="shopping-cart dark" style="min-height: 500px;">
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


