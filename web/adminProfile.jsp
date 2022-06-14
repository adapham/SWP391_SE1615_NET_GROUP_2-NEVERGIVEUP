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
                <!-- Main Content -->
                <div id="content">
                    <div class="container-xl px-4 mt-4">
                        <!-- Account page navigation-->
                        <hr class="mt-0 mb-4">
                        <div class="row">
                            <c:forEach items="${list}" var="l">
                                <div class="col-xl-4">
                                    <!-- Profile picture card-->
                                    <div class="card mb-4 mb-xl-0">
                                        <div class="card-header">Profile Picture</div>
                                        <div class="card-body text-center">
                                            <!-- Profile picture image-->
                                            <img class="img-account-profile rounded-circle mb-2" src="${l.imageURL}" alt="" style="min-width: 60%;">
                                            <!-- Profile picture help block-->
                                            <div class="small font-italic text-muted mb-4">
                                                <br>
                                                <h3>
                                                    ${l.displayname}
                                                </h3>
                                            </div>
                                             
                                            <!-- Profile picture upload button-->
                                            <button class="btn btn-primary" ><a href="adminHome" style="color: white; text-decoration: none;">Back to Admin Home</a></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-8">
                                    <!-- Account details card-->
                                    <div class="card mb-4">
                                        <div class="card-header">Update Profile</div>
                                        <c:if test="${mess != null}"> 
                                            <br>
                                            <div class="alert alert-danger" role="alert">
                                                ${mess}
                                            </div> 
                                        </c:if>
                                        <div class="card-body">
                                            <form action="adminProfile?do=updateAdminProfile" method="POST">
                                                <!-- Form Row-->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (Username)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="Username">User Name</label>
                                                        <input readonly="" class="form-control" id="Username" type="text" placeholder="Enter your user name..." value="${l.username}">
                                                    </div>
                                                    <!-- Form Group (last name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="DisplayName">Display Name</label>
                                                        <input maxlength="50" name="displayname" class="form-control" id="DisplayName" type="text" placeholder="Enter your new display name..." value="${l.displayname}">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="Address">Address</label>
                                                        <input maxlength="50" name="address" class="form-control" id="Address" type="text" placeholder="Enter your Address..." value="${l.address}">
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="Email">Email</label>
                                                        <input maxlength="50" name="email" class="form-control" id="Email" type="text" placeholder="Enter your Email..." value="${l.email}">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="Phone">Phone</label>
                                                        <input maxlength="10" name="phone" class="form-control" id="Phone" type="text" placeholder="Enter your Phone..." value="${l.phone}">
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="ImageURL">ImageURL</label>
                                                        <input name="imageURL" class="form-control" id="ImageURL" type="text" placeholder="Enter your url image..." value="${l.imageURL}">
                                                    </div>
                                                </div>
                                                <!-- Save changes button-->
                                                <button name="submit" class="btn btn-primary" type="submit">Save changes</button>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Change Password-->
                                    <div class="card mb-4">
                                        <div class="card-header">Change Password</div>
                                        <c:if test="${mess1 != null}"> 
                                            <br>
                                            <div class="alert alert-danger" role="alert">
                                                ${mess1}
                                            </div> 
                                        </c:if>
                                        <div class="card-body">
                                            <form action="adminProfile?do=changePass" method="POST">
                                                <!-- Form Row-->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (first name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="oldPassword">Old Password</label>
                                                        <input name="oldPassword" class="form-control" id="oldPassword" type="password" placeholder="Enter your old password" value="">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="newPassword">New Password</label>
                                                        <input name="newPassword" class="form-control" id="newPassword" type="password" placeholder="Enter your new password" value="">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="comfirmPassword">Comfirm Password</label>
                                                        <input name="comfirmPassword" class="form-control" id="comfirmPassword" type="password" placeholder="Enter your password comfirm" value="">
                                                    </div>
                                                </div>
                                                <!-- Save changes button-->
                                                <button name="submit" class="btn btn-primary" type="submit">Save changes</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
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