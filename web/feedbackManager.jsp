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
                    <div class="card-header py-3">
                        <h1 class="m-0 font-weight-bold text-primary" style="text-align: center">List feedbacks</h1>
                    </div>
                    <form action="feedbackManager?do=searchFeedbacks" method="POST" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input name="keySearch" type="text" class="form-control bg-light border-2 small" placeholder="Search by product name..."
                                   aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button name="submit" class="btn btn-primary" type="submit">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <c:if test="${mess != null}"><h4>${mess}</h4></c:if>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Feedback ID</th>
                                    <th scope="col">Display name</th>
                                    <th scope="col">Product name</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Delete</th>
                                    <th scope="col">Detail</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listFeedBack}" var="l">
                                <tr>
                                    <th scope="row">${l.feedbackID}</th>
                                    <td>${l.disPlayName}</td>
                                    <td>${l.productName}</td>
                                    <td>${l.timeComment}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModa${l.feedbackID}">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                        <!-- The Modal -->

                                        <div class="modal" id="myModa${l.feedbackID}">
                                            <div class="modal-dialog">
                                                <div class="modal-content" style="width: 60%;margin: auto;text-align: center;">
                                                    <!-- Modal Header -->
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Delete feedback</h4>
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    </div>
                                                    <!-- Modal body -->
                                                    <div class="modal-body">
                                                        Are you sure you want to delete the feedback?
                                                    </div>
                                                    <!-- Modal footer -->
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                        <button type="button" class="btn btn-danger"><a href="feedbackManager?do=deleteFeedback&feedbackid=${l.feedbackID}&page=${page}&keySearch=${keySearch}">Confirm</a></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td><a href="feedbackManager?do=detailFeedBack&iD=${l.feedbackID}">Detail</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- /.container-fluid -->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination" style="display: flex; justify-content: center;">
                            <c:choose>
                                <c:when test="${search!=null}">
                                    <c:choose>
                                        <c:when test="${listFeedBack ==null || listFeedBack.size() ==0}">
                                            <h2 style="color: red">NOT FOUND</h2>
                                        </c:when> 
                                        <c:otherwise>
                                            <c:if test="${page!=1}">
                                                <li class="page-item"><a class="page-link" href="feedbackManager?do=searchFeedbacks&page=${page-1}&keySearch=${keySearch}">Previous</a></li>   
                                                </c:if>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="feedbackManager?do=searchFeedbacks&page=${i}&keySearch=${keySearch}">${i}</a></li>
                                                </c:forEach>
                                                <c:if test="${page !=totalPage}">
                                                <li class="page-item"><a class="page-link" href="feedbackManager?do=searchFeedbacks&page=${page+1}&keySearch=${keySearch}">Next</a></li>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${page!=1}">
                                        <li class="page-item"><a class="page-link" href="feedbackManager?do=listFeedback&page=${page-1}">Previous</a></li>   
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li  class="page-item ${page==i?"active":""}"><a class="page-link"  href="feedbackManager?do=listFeedback&page=${i}">${i}</a></li>
                                        </c:forEach>
                                        <c:if test="${page !=totalPage}">
                                        <li class="page-item"><a class="page-link" href="feedbackManager?do=listFeedback&page=${page+1}">Next</a></li>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
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
