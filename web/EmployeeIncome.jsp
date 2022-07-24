

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
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="component/EmployeeSlidebarComponent.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file="component/EmployeeTopbarComponent.jsp" %>

                    <!-- Begin Page Content -->
                    <main class="main-content position-relative border-radius-lg ">
                        <div class="container-fluid py-4">
                            <p><h3 style="color: black;text-align: center"><b>Income</b></h3></p> 
                            <form action="employeeincome" method="post">
                                <select onchange="this.form.submit()" class="form-select" aria-label="Default select example" name="bill">
                                    <option value="0" ${bill == 0 ? " selected" : ""}>All Bill</option>
                                    <option value="1" ${bill == 1 ? " selected" : ""}>Bill today</option>
                                    <option value="3" ${bill == 3 ? " selected" : ""}>Bill 3 day ago </option>
                                    <option value="7" ${bill == 7 ? " selected" : ""}>Bill 1 week ago</option>
                                </select>
                            </form>
                            <p style="color: red;margin-left: 71%"><b>Revenue: </b>$${Math.round((totalmoney*100)/100)}</p> 

                            <table class="border table table-striped table-hover table-bordered border-primary"  width="100%" cellspacing="0">
                                <thead>
                                    <tr> 
                                        <th style="color: black">OrderID</th>
                                        <th style="color: black">Order Date</th>
                                        <th style="color: black">Status</th>   
                                        <th style="color: black">Total</th>
                                        <th style="color: black">Details</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="l" >
                                        <tr>
                                            <td style="color: black">${l.orderID}</td>
                                            <td style="color: black">${l.orderDate}</td>
                                            <c:if test="${l.status ==1}"><td style="color: black">Wait</td></c:if>  
                                            <c:if test="${l.status ==2}"><td style="color: black">Process</td></c:if>  
                                            <c:if test="${l.status ==3}"><td style="color: black">Done</td></c:if>  
                                            <td style="color: black">$${l.total}</td>
                                            <td><a href="employeeincome?do=details&odID=${l.orderID}">Details</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <br>

                        </div>
                    </main>
                    <!-- /.container-fluid -->

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
                        <a class="btn btn-primary" href="login?do=logout">Logout</a>
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
