<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/main.css" rel="stylesheet" />          
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/forget-password.css">
        <title>Forget password Template | Bootcatch themes</title>
    </head>
    <body>
        <div class="d-flex align-items-center light-blue-gradient" style="height: 100vh;">
            <div class="container" >
                <div class="d-flex justify-content-center">
                    <div class="col-md-7">
                        <div class="card rounded-0 shadow">
                            <div class="card-body">
                                <h3>Update Password</h3>
                                <form action="login?do=updatepassword" method="post">
                                    <div class="form-group">
                                        <label >Password:</label>
                                        <input type="text" name="email" hidden="" value="${email}" >
                                        <input type="text" name="pass" hidden="" value="${pass}" >
                                        <input type="password" class="form-control" id="exampleInputEmail1" placeholder="Password" name="password" >
                                        <label >New Password:</label>
                                        <input type="password" class="form-control" id="exampleInputEmail1" placeholder="New Password" name="newpassword" >
                                        <label >Confirm Password:</label>
                                        <input type="password" class="form-control" id="exampleInputEmail1" placeholder="Confirm Password" name="confirmpassword" >
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Submit</button> 
                                    <c:if test="${mess !=null}">${mess}</c:if>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>

                <!-- Optional JavaScript -->
                <!-- jQuery first, then Popper.js, then Bootstrap JS -->
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
            </div>
    </body>
</html>