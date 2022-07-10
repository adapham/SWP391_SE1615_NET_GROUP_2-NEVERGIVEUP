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
                    <h1 style="text-align: center" class="h3 mb-2 text-gray-800">Create Account</h1>
                    <c:if test="${mess !=null}"><h6 style="color: red">${mess}</h6></c:if>  
                        <!-- DataTales Example -->
                        <form action="accountmanager?do=createAccount" method="post">
                            <div class="row">
                                <div class="col">
                                    <h6 style="color: black">User Name:</h6>  <input maxlength="50" type="text" class="form-control" placeholder="UserName..." name="username" value="${username}">
                            </div>
                            <div class="col">
                                <h6 style="color: black">Password:</h6>   <input maxlength="50" type="password" class="form-control" placeholder="Password..." name="password" value="${password}">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col">
                                <h6 style="color: black">Display Name:</h6>  <input maxlength="50" type="text" class="form-control" placeholder="Displayname..." name="displayname" value="${displayname}">
                            </div>
                            <div class="col">
                                <h6 style="color: black">Address:</h6>   <input maxlength="50" type="text" class="form-control" placeholder="Address..." name="address" value="${address}">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col">
                                <h6 style="color: black">Email:</h6>  <input maxlength="50" type="email" class="form-control" placeholder="Email..." name="email" value="${email}">
                            </div>
                            <div class="col">
                                <h6 style="color: black">Phone:</h6>   <input maxlength="10" type="number" class="form-control" placeholder="Phone..." name="phone" value="${phone}">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col">
                                <h6 style="color: black">Choose File Image:</h6> 
                                <input class="form-control" placeholder="ImageURL..." type="text" name="imageURL" value="${imageURL}">
                            </div>
                            <div class="col">
                                <h6 style="color: black">Permission:</h6> 
                                <select class="form-select" aria-label="Default select example" name="role">
                                    <c:choose>
                                        <c:when test="${role ==2}">
                                            <option selected="" value="2">Employee</option>
                                            <option value="4">Shipper</option>
                                        </c:when> <c:otherwise>
                                            <option  value="2">Employee</option>
                                            <option selected="" value="4">Shipper</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>  
                            </div>
                        </div>
                        <br>
                        <h6 style="color: black">Gender:</h6>
                        <c:choose>
                            <c:when test="${gender ==1}">
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

                        <br>
                        <div class="d-flex justify-content-around"><button type="submit" name="submit" value="submit" class="btn btn-success ">Create</button></div>
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
