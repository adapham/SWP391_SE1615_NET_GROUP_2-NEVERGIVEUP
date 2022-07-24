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
        <link href="css/sb-admin-2.css" rel="stylesheet">   
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
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



                    <!------------------CODE HERE-------------------------->

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary" style="text-align: center">View List Account of Customer</h6>
                        </div>
                        <form  action="employeeaccount?do=searchCustomer" style="margin: auto" method="post">
                            <input type="text" placeholder="Search.." name="search" value="${search}">
                            <button type="submit" ><i class="fa fa-search"></i></button>
                        </form>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table   width="100%" cellspacing="0" class="border table table-striped table-hover table-bordered border-primary">
                                    <thead>
                                        <tr>
                                            <th style="color: black">User name</th>
                                            <th style="color: black">Displayname</th>
                                            <th style="color: black">Address</th>
                                            <th style="color: black">Email</th>
                                            <th style="color: black">Phone</th>
                                            <th style="color: black">ImageURL</th>
                                            <th style="color: black">Details Order</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${listAccount}" var="l">
                                        <tbody>
                                            <tr>
                                                <td style="color: black">${l.username}</td>
                                                <td style="color: black">${l.displayname}</td>
                                                <td style="color: black">${l.address}</td>
                                                <td style="color: black">${l.email}</td>
                                                <td style="color: black">${l.phone}</td>
                                                <td> <img src="${l.imageURL}" height="50px"></td>
                                                <td><a href="employeeaccount?do=DetailOrder&accountid=${l.accountid}">Detail Order</a></td>
                                            </tr>
                                        </tbody>
                                    </c:forEach>
                                </table>
                            </div>
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center" >

                                <ul class="pagination">
                                    <c:choose>
                                        <c:when test="${keysearch!=null}">
                                            <c:choose>
                                                <c:when test="${listAccount ==null || listAccount.size() ==0}">
                                                    <h2 style="color: red">NOT FOUND</h2>
                                                </c:when> 
                                                <c:otherwise>
                                                    <c:if test="${page!=1}">
                                                        <li class="page-item"><a class="page-link" href="employeeaccount?do=searchCustomer&page=${page-1}&search=${search}">Previous</a></li>   
                                                        </c:if>
                                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                                        <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="employeeaccount?do=searchCustomer&page=${i}&search=${search}">${i}</a></li>
                                                        </c:forEach>
                                                        <c:if test="${page !=totalPage}">
                                                        <li class="page-item"><a class="page-link" href="employeeaccount?do=searchCustomer&page=${page+1}&search=${search}">Next</a></li>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${page!=1}">
                                                <li class="page-item"><a class="page-link" href="employeeaccount?do=AccountCustomer&page=${page-1}">Previous</a></li>   
                                                </c:if>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="employeeaccount?do=AccountCustomer&page=${i}">${i}</a></li>
                                                </c:forEach>
                                                <c:if test="${page !=totalPage}">
                                                <li class="page-item"><a class="page-link" href="employeeaccount?do=AccountCustomer&page=${page+1}">Next</a></li>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>



                                </ul>
                            </nav>
                        </div>
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
                        <!----------------------------------------------------------->






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
                <!--                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
                                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->

                </body>
                </html>
