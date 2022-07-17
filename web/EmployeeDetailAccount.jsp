

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
                            <div style="text-align: center">
                                <c:forEach items="${list}" var="l" begin="1" end="1" >
                                    <p><h3 style="color: black"><b> Information customer:  </b></h3></p> 
                                    <p style="color: black"><b>Customer name: </b>${l.displayname}</p> 
                                    <p style="color: black"><b>Email: </b>${l.email}</p> 
                                    <p style="color: black"><b>Phone: </b>${l.phone}</p>
                                </c:forEach>  
                            </div>

                            <c:forEach items="${listOrderId}" var="lo">    
                            <p><h3 style="color: black;text-align: center"><b>Details bill</b></h3></p>  
                                <table class="table table-bordered"  width="100%" cellspacing="0">
                                    <thead>
                                        <tr> 
                                            <th style="color: black">ImageURL</th>
                                            <th style="color: black">ProductName</th>
                                            <th style="color: black">Price</th>   
                                            <th style="color: black">Quantity</th>
                                            <th style="color: black">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${list}" var="l" >
                                            <c:if test="${l.orderID eq lo}">
                                                <tr>
                                                    <td>
                                                        <img src="${l.imageURL}" alt="" style="height: 90px; width: 40%">
                                                    </td>
                                                    <td style="color: black">${l.productName}</td>
                                                    <td style="color: black">$${l.price}</td>
                                                    <td style="color: black;text-align: center" >${l.quantity}</td>
                                                    <td style="color: black">$${Math.round(l.total*100)/100}</td>
                                                </tr>
                                            <input hidden="" ${Total = Total + Math.round(l.total*100)/100}> 
                                            <input hidden="" ${address = l.address}> 
                                            <input hidden="" ${status = l.status}>
                                            <input hidden="" ${orderdate = l.orderDate}> 
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div style="text-align: center">
                                    <p style="color: black"><b>Order date: </b>${orderdate}</p>
                                    <p style="color: black"><b>Total amount: </b>$${Total}</p>
                                    <p style="color: black"><b>Ship address: </b>${address}</p>
                                    <c:if test="${status ==1}"><p style="color: black"><b>Status: </b>Wait</p></c:if>
                                    <c:if test="${status ==2}"><p style="color: black"><b>Status: </b>Process</p></c:if>
                                    <c:if test="${status ==3}"><p style="color: black"><b>Status: </b>Done</p></c:if>
                                    </div>

                                    <input hidden=""  ${Total =0}>
                                <br><br>
                            </c:forEach>
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
