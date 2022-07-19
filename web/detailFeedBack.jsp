<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="assets/css/style.css">
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
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->

                    <div class="breadcrumb-content text-center">
                        <ul>
                            <li>
                                <a href="feedbackManager?do=listFeedback"><h3>List feedbacks</h3></a>
                            </li>
                            <li class="active">Detail feedback</li>
                        </ul>
                    </div>
                    <c:forEach items="${listFeedBack}" var="l">
                        <div>
                            <img src="${l.imageURL}" style="margin-left: 39%; padding-top: 5%">
                        </div>
                        <div>
                            <h3 style="text-align: center; padding-top: 15px; padding-bottom: 5%">${l.productName}</h3>
                        </div>
                    </c:forEach> 

                    <div>
                        <h1 style="margin-left: 25%">Detail feedback</h1>
                        <table class="table table-bordered" style="border: 2px solid black; width: 50%; margin-left: 25%">                                        
                            <tbody>
                                <c:forEach items="${listFeedBack}" var="l">
                                    <tr>                
                                        <td style="color: black; font-size: 20px">Display name: </td><td style="color: black">${l.disPlayName}</td>                                          
                                    </tr>
                                    <tr>                
                                        <td style="color: black; font-size: 20px">Product name: </td><td style="color: black">${l.productName}</td>                                           
                                    </tr>
                                    <tr>                
                                        <td style="color: black; font-size: 20px">Content: </td><td style="color: black">${l.feedbackContent}</td>                                            
                                    </tr>
                                    <tr>                
                                        <td style="color: black; font-size: 20px">Date: </td><td style="color: black">${l.timeComment}</td>                                          
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
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
