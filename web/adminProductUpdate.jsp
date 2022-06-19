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
                                        <div class="card-header">Product Picture</div>
                                        <div class="card-body text-center">
                                            <!-- Profile picture image-->
                                            <img class="img-account-profile rounded-circle mb-2" src="${l.imageURL}" alt="" style="min-width: 60%;">
                                            <!-- Profile picture help block-->
                                            <div class="small font-italic text-muted mb-4">
                                                <br>
                                                <h3>
                                                    ${l.productName}
                                                </h3>
                                            </div>

                                            <!-- Profile picture upload button-->
                                            <button class="btn btn-primary" ><a href="adminProduct" style="color: white; text-decoration: none;">Back to Products Manager</a></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-8">
                                    <!-- Account details card-->
                                    <div class="card mb-4">
                                        <div class="card-header"><a href="adminProduct">Product </a>/ Update Product</div>
                                        <c:if test="${mess != null}"> 
                                            <br>
                                            <div class="alert alert-danger" role="alert">
                                                ${mess}
                                            </div> 
                                        </c:if>
                                        <div class="card-body">
                                            <form action="adminProduct?do=updateProduct" method="POST">
                                                <!-- Form Row-->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (Username)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="pID">Product ID</label>
                                                        <input name="pID" readonly="" class="form-control" id="pID" type="text" placeholder="" value="${l.productID}">
                                                    </div>
                                                    <!-- Form Group (last name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="productName">Product Name</label>
                                                        <input maxlength="50" name="productName" class="form-control" id="productName" type="text" placeholder="Enter your new product name..." value="${l.productName}">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="supplierID">Supplier ID</label>
                                                        <select style="width: 100%;height: 35px;border-radius: 5px;border-color: #ccc;" name="supplierID" class="form-select" aria-label="Default select example" class="form-control" id="supplierID">
                                                            <c:forEach items="${listSup}" var="ls">
                                                                <option value="${ls.supplierID}" ${l.supplierID == ls.supplierID ? " selected":""} >  ${ls.companyName} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="categoryID">Category ID</label>
                                                        <select name="categoryID" style="width: 100%;height: 35px;border-radius: 5px;border-color: #ccc;" class="form-select" aria-label="Default select example" class="form-control" id="categoryID">
                                                            <c:forEach items="${listCate}" var="lc">
                                                                <option value="${lc.categoryID}" ${l.categoryID == lc.categoryID ? " selected":""} >  ${lc.categoryName} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="quantity">Quantity</label>
                                                        <input min="0"  name="quantity" class="form-control" id="quantity" type="number" placeholder="Enter your quantity..." value="${l.quantity}">
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="unitPrice">Unit Price</label>
                                                        <input min="0" step=any name="unitPrice" class="form-control" id="unitPrice" type="number" placeholder="Enter your price..." value="${l.unitPrice}">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="discount">Discount</label>
                                                        <input step="0.01" min="0" max="1" name="discount" class="form-control" id="discount" type="number" placeholder="Enter your Phone..." value="${l.discount}">
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="unitInStock">Unit In Stock</label>
                                                        <input min="0" name="unitInStock" class="form-control" id="unitInStock" type="number" placeholder="Enter your unit in stock..." value="${l.unitInStock}">
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="description">Description</label>
                                                        <textarea maxlength="100"  style="height: 100px;" name="description" class="form-control" id="description" type="text" placeholder="Enter your Description..." value="">${l.description}</textarea>
                                                    </div>
                                                    <!-- Form Group (location)-->
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="isActive">Is Active</label>
                                                        <br>
                                                        <div style="margin-top: 20px; font-size: 25px;">
                                                            <input style="margin-left: 10px;" type="radio" value="0" ${l.isActive == 0 ? " checked":""} name="isActive">  No
                                                            <input style="margin-left: 10px;" type="radio" value="1" ${l.isActive == 1 ? " checked":""} name="isActive">  Yes
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->
                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (organization name)-->
                                                    <div class="col-md-12">
                                                        <label class="small mb-1" for="imageURL">Images URL</label>
                                                        <input name="imageURL" class="form-control" id="imageURL" type="text" placeholder="Enter your Phone..." value="${l.imageURL}">
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