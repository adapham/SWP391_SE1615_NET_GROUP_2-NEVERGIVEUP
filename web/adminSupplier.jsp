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
                <h1 class="text-center text-primary">List All Supplier</h1>
                <!--Main COntent-->
                <div id="content">
                    <div class="d-flex justify-content-between" style="background-color: #ccc; padding:10px 0;">
                        <div class="same-style-2 header-search-1">
                            <!-- Topbar Search -->
                            <form action="adminSupplier?do=searchSupplier" method="POST" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                <div class="input-group">
                                    <input value="${keySearch}" name="searchKey" type="text" class="form-control bg-light border-2 small" placeholder="Search company name..."
                                           aria-label="Search" aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button name="submit" class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div style="margin-right: 20px;">
                            <button type="button" class="btn btn-outline-success"><a href="adminSupplier?do=createSupplier" style="text-decoration: none;"><i class="fas fa-plus"></i> Add new Category</a></button>
                        </div>
                    </div>
                    <c:if test="${mess != null}"> 
                        <br>
                        <div class="alert alert-danger" role="alert">
                            ${mess}
                        </div> 
                    </c:if>
                    <!-- Main Content -->
                    <div class="col col-md-12">
                        <table class="border table table-striped table-hover table-bordered border-primary text-center" style="margin-top: 10px">
                            <thead class="bg-info" >
                                <tr>
                                    <th>Supplier ID</th>
                                    <th>Company Name</th>
                                    <th>Address</th>
                                    <th>Phone</th>
                                    <th>Email</th>
                                    <th>Edit</th>
                                    <th>Delete</th>    
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${supplierList}" var="l">
                                    <tr>
                                        <td style="text-align: right;">${l.supplierID}</td>
                                        <td style="text-align: left;">${l.companyName}</td>
                                        <td style="text-align: left;">${l.address}</td>
                                        <td style="text-align: left;">${l.phone}</td>
                                        <td style="text-align: left;">${l.email}</td>
                                        <td><a href="adminSupplier?do=updateSupplier&supID=${l.supplierID}"><i class="fas fa-edit"></i></a></td>
                                        <td>
                                            <button style="color: red; border: none;" type="button" data-toggle="modal" data-target="#myModal${l.supplierID}">
                                                <i class="fas fa-trash"></i>
                                            </button>

                                            <!-- The Modal -->
                                            <div class="modal" id="myModal${l.supplierID}">
                                                <div class="modal-dialog">
                                                    <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Delete product</h4>
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <!-- Modal body -->
                                                        <div class="modal-body">
                                                            Are you sure you want to delete the supplier?
                                                        </div>
                                                        <!-- Modal footer -->
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-info" data-dismiss="modal">No</button>
                                                            <button type="button" class="btn btn-danger"><a style="text-decoration: none; color: white;" href="adminSupplier?do=deleteSupplier&supID=${l.supplierID}">Yes</a></button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!--Paging-->
                        <!--Paging Category-->
                        <c:if test="${sessionScope.backToUrl eq \"adminSupplier\"}">
                            <c:choose>
                                <c:when test="${supplierList == null || supplierList.size() == 0}">
                                    Not FOUND
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                        <ul class="pagination">
                                            <c:if test="${page > 1}">
                                                <li class="page-item"><a class="page-link" href="adminSupplier?do=supplierHome&page=${page - 1}">Previous</a></li>
                                                </c:if>
                                                <c:if test="${totalPage != 1}">
                                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                                    <li class="page-item ${i == page?" active":""}"><a class="page-link" href="adminSupplier?do=supplierHome&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${page < totalPage}">
                                                <li class="page-item"><a class="page-link" href="adminSupplier?do=supplierHome&page=${page + 1}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <!--Paging Search-->
                        <c:if test="${sessionScope.backToUrl eq \"adminSupplier?do=searchSupplier\"}">
                            <c:choose>
                                <c:when test="${supplierList == null || supplierList.size() == 0}">
                                    Not FOUND
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                        <ul class="pagination">
                                            <c:if test="${page > 1}">
                                                <li class="page-item"><a class="page-link" href="adminSupplier?do=searchSupplier&page=${page - 1}&searchKey=${keySearch}">Previous</a></li>
                                                </c:if>
                                                <c:if test="${totalPage != 1}">
                                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                                    <li class="page-item ${i == page?" active":""}"><a class="page-link" href="adminSupplier?do=searchSupplier&page=${i}&searchKey=${keySearch}">${i}</a></li>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${page < totalPage}">
                                                <li class="page-item"><a class="page-link" href="adminSupplier?do=searchSupplier&page=${page + 1}&searchKey=${keySearch}">Next</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
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


        <!--Scrip Jquery modal-->
        <!--        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->  
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