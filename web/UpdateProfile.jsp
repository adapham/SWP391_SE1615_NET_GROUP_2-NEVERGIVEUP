<%@page import="java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootstrap snippets bootdeys">
    <div class="row">
        <div class="col-xs-12 col-sm-9">
            <form class="form-horizontal" action="login?do=updateprofile" method="post">
                <c:forEach items="${list}" var="l">
                    <div class="panel panel-default">
                        <div class="panel-body text-center">                                 

                            <img src="${l.imageURL}" name="imageURL" class="img-circle profile-avatar" alt="User avatar">
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">USER INFOMATION</h4>

                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">UserName</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.username}" readonly="" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">DisplayName</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.displayname}" name="displayname">

                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Address</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.address}" name="address">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.email}" name="email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Phone</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.phone}" name="phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">ImageURL</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${l.imageURL}" name="imageURL">
                                </div>
                            </div>
                            <c:if test="${mess != null}"> ${mess} </c:if>
                            </div>

                        </div>
                </c:forEach>  
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <button type="submit" name="submit" value="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-default">Cancel</button>
                    </div>
                </div>
            </form>
            <form class="form-horizontal" action="login?do=changepassword" method="post">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">CHANGE PASSWORD</h4>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">PassWord</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">New PassWord</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="newpassword" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Confirm PassWord</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="confirmpassword" >
                            </div>
                        </div>
                        <c:if test="${mess1 != null}"> ${mess1} </c:if>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <button type="submit" name="submit" value="submit" class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-default">Cancel</button>
                            </div>
                            <a href="home">Home</a>

                        </div>
                    </div>
                </div>
            </form>



        </div>
    </div>
</div>