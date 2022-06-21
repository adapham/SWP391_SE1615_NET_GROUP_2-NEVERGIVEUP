<%-- 
    Document   : admin
    Created on : Jun 10, 2022, 10:22:03 PM
    Author     : KhacBao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    </head>
    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="component/AdminSlidebarComponent.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file="component/AdminTopbarComponent.jsp" %>



                    <!------------------CODE HERE-------------------------->
                    <h1 style="text-align: center" class="h3 mb-2 text-gray-800">Update Account of Shipper</h1>
                        <!-- DataTales Example -->
                        <form action="accountmanager?do=updateShipper" method="post">
                            <table style="border: 1px;margin: auto"  >
                <c:if test="${mess !=null}"><h6 style="color: red;text-align: center">${mess}</h6></c:if>
                            <c:forEach items="${list}" var="l">
                                <input hidden="" name="accountid" value="${l.accountid}" >
                                <input hidden="" name="emailold" value="${l.email}" >
                                <input hidden="" name="phoneold" value="${l.phone}" >
                                <input hidden="" name="password" value="${l.password}" >
                                <tr>
                                    
                                    <td>User Name:</td>
                                    <td><input maxlength="50" type="text" name="username" readonly="" value="${l.username}"></td>
                                </tr>
                                <tr>
                                    <td>Display Name:</td>
                                    <td><input maxlength="50" type="text" name="displayname" value="${l.displayname}"></td>
                                </tr>
                                <tr>
                                    <td>Address:</td>
                                    <td><input maxlength="50" type="text" name="address" value="${l.address}"></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><input type="email" name="email" value="${l.email}"></td>
                                </tr>
                                <tr>
                                    <td>Phone:</td>
                                    <td><input maxlength="10" type="number" name="phone" value="${l.phone}"></td>
                                </tr>
                                <tr>
                                    <td>ImageURL:</td>
                                    <td><input type="text" name="imageURL" value="${l.imageURL}"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>  <input type="submit" name="submit" value="submit"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                    <!----------------------------------------------------------->

                    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>




                    <!-- End of Main Content -->

                    <!-- Footer -->
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; Your Website 2021</span>
                            </div>
                        </div>
                    </footer>
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

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
                            <a class="btn btn-primary" href="login.html">Logout</a>
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
