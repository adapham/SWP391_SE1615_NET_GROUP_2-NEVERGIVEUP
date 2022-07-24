<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>ADA-Shop</title>
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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

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
                            <li class="active">My Orders</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="">
                <div class="container">
                    <h3 class="cart-page-title">My Orders</h3>
                    <c:if test="${mess !=null}"><h4 style="color: red">${mess}</h4></c:if>
                    <form action="MyCartController" method="POST" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input name="keySearch" value="" type="date" class="form-control bg-light border-2 small" placeholder="Search by address..."
                                   aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">

                                <button name="submit" value="submit" class="btn btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="table-content table-responsive cart-table-content">
                                <table id="table_id">
                                    <thead> 

                                        <tr>
                                            <th>ID</th>
                                            <th>Date</th>
                                            <th>Address</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Status</th>


                                            <th>Delete</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${list}" var="pro">
                                            <tr>
                                                <td ><a href="MyCartController?do=ordersDetail&orderID=${pro.orderID}">${pro.orderID}</a> </td>
                                                <td>${pro.orderDate}</td>
                                                <td >${pro.address}</td>
                                                <td >${pro.email }</td>
                                                <td >${pro.phone}</td>

                                                <td > 
                                                    <c:if test="${pro.status == 1 }">    
                                                        <b>${pro.status == 1 ? " New" : ""}</b>
                                                    <td >  
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModa${pro.orderID}">
                                                            Delete
                                                        </button>
                                                        <!-- The Modal -->

                                                        <div class="modal" id="myModa${pro.orderID}">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                                    <!-- Modal Header -->
                                                                    <div class="modal-header">
                                                                        <h4 class="modal-title">Delete order</h4>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <!-- Modal body -->
                                                                    <div class="modal-body">
                                                                        Are you sure you want to delete the order?
                                                                    </div>
                                                                    <!-- Modal footer -->
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                                                        <button type="button" class="btn btn-danger"><a href="MyCartController?do=delete&page=${page}&id=${pro.orderID}&keySearch=${keySearch}">OK</a></button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td> 
                                                </c:if>
                                                <c:if test="${pro.status == 2 }">
                                            <b>${pro.status == 2 ? " Process" : ""}</b>
                                        </c:if>
                                        <c:if test="${pro.status == 3 }">
                                            <b>${pro.status == 3 ? " Done" : ""}</b>
                                        </c:if>
                                        </td>
                                        </tr>

                                    </c:forEach>




                                    </tbody>

                                </table>
                            </div>

                        </div>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex; justify-content: center;">
                            <c:choose>
                                <c:when test="${keySearch != null}">
                                    <c:if test="${page!=1}">
                                        <li class="page-item"><a class="page-link" href="MyCartController?page=${page-1}&keySearch=${keySearch}">Previous</a></li>   
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="MyCartController?page=${i}&keySearch=${keySearch}">${i}</a></li>
                                        </c:forEach>
                                        <c:if test="${page!=totalPage}">
                                        <li class="page-item"><a class="page-link" href="MyCartController?page=${page+1}&keySearch=${keySearch}">Next</a></li>
                                        </c:if>
                                    </c:when><c:otherwise>
                                        <c:if test="${page!=1}">
                                        <li class="page-item"><a class="page-link" href="MyCartController?page=${page-1}">Previous</a></li>   
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="MyCartController?page=${i}">${i}</a></li>
                                        </c:forEach>
                                        <c:if test="${page!=totalPage}">
                                        <li class="page-item"><a class="page-link" href="MyCartController?page=${page+1}">Next</a></li>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                        </ul>
                    </nav>
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