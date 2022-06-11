<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>SB Admin 2 - Dashboard</title>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/mains.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    </head>
    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="component/AdminSlidebarComponent.jsp" %>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Topbar -->
                <%@include file="component/AdminTopbarComponent.jsp" %>
                <!-- End of Topbar -->
                <div class="d-flex justify-content-between" style="background-color: #ccc; padding:10px 0;">
                    <div class="same-style-2 header-search-1">
                        <!-- Topbar Search -->
                        <form
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-2 small" placeholder="Search product name..."
                                       aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <button type="button" class="btn btn-outline-success"><i class="fas fa-plus"></i> Add new Product</button>
                    </div>
                </div>
                <!-- Main Content -->
                <div class="col-md-12">
                    <table class="border table table-striped table-hover table-bordered border-primary text-center" style="margin-top: 10px">
                        <thead class="bg-info" style="position: -webkit-sticky; position: sticky; top: 0;">
                            <tr>
                                <th>ProductID<i class="fas fa-sort-numeric-down"></i></th>
                                <th>Product Name<i class="fas fa-sort-alpha-down"></i></th>
                                <th>SupplierID<i class="fas fa-sort-numeric-down"></i></th>
                                <th>CategoryID<i class="fas fa-sort-numeric-down"></i></th>
                                <th>Quantity<i class="fas fa-sort-numeric-down"></i></th>
                                <th>UnitPrice<i class="fas fa-sort-numeric-down"></i></th>
                                <th>Discount<i class="fas fa-sort-numeric-down"></i></th>
                                <th>UnitInStock<i class="fas fa-sort-numeric-down"></i></th>
                                <th>Description<i class="fas fa-sort-alpha-down"></i></th>
                                <th>Images</th>
                                <th>IsActive</th>
                                <th>Edit</th>
                                <th>Delete</th>    
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProduct}" var="l">
                                <tr>
                                    <td>${l.productID}</td>
                                    <td>${l.productName}</td>
                                    <td>${l.supplierID}</td>
                                    <td>${l.categoryID}</td>
                                    <td>${l.quantity}</td>
                                    <td>${l.unitPrice}</td>
                                    <td>${l.discount}</td>
                                    <td>${l.unitInStock}</td>
                                    <td>${l.description}</td>
                                    <td><img src="${l.imageURL}" style="width: 100px"></td>
                                    <td>
                                        <c:if test="${l.isActive == 1}">
                                            <i style="color: green;" class="fas fa-toggle-on"></i>
                                        </c:if>
                                        <c:if test="${l.isActive == 0}">
                                            <i style="color: red;" class="fas fa-toggle-off"></i>
                                        </c:if>
                                    </td>
                                    <td><a href="adminProduct?do=updateProduct&pID=${l.productID}"><i class="fas fa-edit"></i></a></td>
                                    <td><a href="#"><i class="fas fa-trash"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- End of Main Content -->
                <!-- Footer -->
                <%@include file="component/AdminFooterComponent.jsp" %>
                <!-- End of Footer -->
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="adminProfile?do=logout">Logout</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>
        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
        <script src="js/demo/chart-bar-demo.js"></script>
    </body>
</html>