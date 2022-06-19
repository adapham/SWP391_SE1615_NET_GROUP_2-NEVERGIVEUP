<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="adminHome">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Food Admin <sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Heading -->
    <div class="sidebar-heading">
        Overview
    </div>

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="adminHome">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Product Manager
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct"
           aria-expanded="true" aria-controls="collapseProduct">
            <i class="fas fa-fw fa-folder"></i>
            <span>Product Manager</span>
        </a>
        <div id="collapseProduct" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Product Manager Crud:</h6>
                <a class="collapse-item" href="adminProduct"> Products</a>
                <a style="padding-left: 30px;" class="collapse-item" href="adminProduct?do=createProduct"><i class="fas fa-plus"></i> Create Product</a>
                <a class="collapse-item" href="adminCategory"> Categories</a>
                <a style="padding-left: 30px;" class="collapse-item" href="adminCategory?do=createCategory"><i class="fas fa-plus"></i> Create Category</a>
                <a class="collapse-item" href="#"> Suppliers</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Account Manager
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAccount"
           aria-expanded="true" aria-controls="collapseAccount">
            <i class="fas fa-fw fa-folder"></i>
            <span>Account Managerment</span>
        </a>
        <div id="collapseAccount" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Account Manager CRUD:</h6>
                <a class="collapse-item" href="#">Customers</a>
                <a class="collapse-item" href="#">Employees</a>
                <a class="collapse-item" href="#">Shippers</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Bill Manager
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrder"
           aria-expanded="true" aria-controls="collapseOrder">
            <i class="fas fa-fw fa-folder"></i>
            <span>Bill Managerment</span>
        </a>
        <div id="collapseOrder" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Account Manager CRUD:</h6>
                <a class="collapse-item" href="#">Orders</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        User Feedback Manager
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFeedback"
           aria-expanded="true" aria-controls="collapseFeedback">
            <i class="fas fa-fw fa-folder"></i>
            <span>Feedback Manager</span>
        </a>
        <div id="collapseFeedback" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">FeedBack Manager CRUD:</h6>
                <a class="collapse-item" href="404.html">404 Page</a>
                <a class="collapse-item" href="#">Feekbacks</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->
