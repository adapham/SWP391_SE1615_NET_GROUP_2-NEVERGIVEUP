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
                    <h1 style="text-align: center" class="h3 mb-2 text-gray-800">Update Account of Customer</h1>
                    <div id="content">
                        <div class="container-xl px-4 mt-4">
                            <!-- Account page navigation-->
                            <hr class="mt-0 mb-4">
                            <div class="row">
                                <c:forEach items="${list}" var="l">
                                    <div class="col-xl-4">
                                        <!-- Profile picture card-->
                                        <div class="card mb-4 mb-xl-0">
                                            <div class="card-header"> Picture</div>
                                            <div class="card-body text-center">
                                                <!-- Profile picture image-->
                                                <img class="img-account-profile rounded-circle mb-2" src="${l.imageURL}" alt="" style="min-width: 60%;width:42px;height:160px;">
                                                <!-- Profile picture help block-->
                                                <div class="small font-italic text-muted mb-4">
                                                    <br>
                                                    <h3>

                                                    </h3>
                                                </div>

                                                <!-- Profile picture upload button-->
                                                <button class="btn btn-primary" ><a href="accountmanager" style="color: white; text-decoration: none;">Back to Account Manager</a></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-8">
                                        <!-- Account details card-->
                                        <div class="card mb-4">
                                            <div class="card-header"><a href="accountmanager">Account Customer </a>/ Update Account Customer</div>
                                            <c:if test="${mess != null}"> 
                                                <br>
                                                <div class="alert alert-danger" role="alert">
                                                    ${mess}
                                                </div> 
                                            </c:if>
                                            <div class="card-body">
                                                <form action="accountmanager?do=updateCustomer" method="post">
                                                    <input hidden="" name="accountid" value="${l.accountid}" >
                                                    <input hidden="" name="emailold" value="${l.email}" >
                                                    <input hidden="" name="phoneold" value="${l.phone}" >
                                                    <input hidden="" name="password" value="${l.password}" >
                                                    <!-- Form Row-->
                                                    <div class="row gx-3 mb-3">
                                                        <!-- Form Group (Username)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >User Name</label>
                                                            <input name="username" readonly="" class="form-control"  type="text" placeholder="" value="${l.username}">
                                                        </div>
                                                        <!-- Form Group (last name)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >Display Name</label>
                                                            <input maxlength="50" name="displayname" class="form-control"  type="text" placeholder="Enter your Display Name..." value="${l.displayname}">
                                                        </div>
                                                    </div>
                                                    <!-- Form Row        -->
                                                    <div class="row gx-3 mb-3">
                                                        <!-- Form Group (organization name)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >Address</label>
                                                            <input name="address" class="form-control" type="text" placeholder="Enter your Address..." value="${l.address}">
                                                        </div>
                                                        <!-- Form Group (location)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >Email</label>
                                                            <input  name="email" class="form-control"  type="email" placeholder="Enter your Email..." value="${l.email}">
                                                        </div>
                                                    </div>
                                                    <!-- Form Row        -->
                                                    <div class="row gx-3 mb-3">
                                                        <!-- Form Group (organization name)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >Phone</label>
                                                            <input maxlength="10" name="phone" class="form-control"  type="phone" placeholder="Enter your Phone..." value="${l.phone}">
                                                        </div>
                                                        <!-- Form Group (location)-->
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" >ImageURL</label>
                                                            <input name="imageURL" class="form-control"  type="text" placeholder="Enter your ImageURL..." value="${l.imageURL}">
                                                        </div>
                                                    </div>
                                                    <div class="row gx-3 mb-3">
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" for="gender">Gender</label>
                                                            <br>
                                                            <div style="margin-top: 20px; font-size: 25px;">
                                                                <c:choose>
                                                                    <c:when test="${l.gender ==1}">
                                                                        <div class="form-check form-check-inline">
                                                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="1" checked="">
                                                                            <label class="form-check-label" for="inlineRadio1" style="color: black">Male</label>
                                                                        </div>
                                                                        <div class="form-check form-check-inline">
                                                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="0">
                                                                            <label class="form-check-label" for="inlineRadio2" style="color: black">Female</label>
                                                                        </div>
                                                                    </c:when><c:otherwise>
                                                                        <div class="form-check form-check-inline">
                                                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="1" >
                                                                            <label class="form-check-label" for="inlineRadio1" style="color: black">Male</label>
                                                                        </div>
                                                                        <div class="form-check form-check-inline">
                                                                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="0" checked="">
                                                                            <label class="form-check-label" for="inlineRadio2" style="color: black">Female</label>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Save changes button-->
                                                    <button name="submit" class="btn btn-primary" type="submit" value="submit">Save changes</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- DataTales Example -->

                    
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
