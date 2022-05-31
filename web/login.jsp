<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet" />
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle">Login or <a href="register">Sign up</a></h3>
        <div class="row omb_row-sm-offset-3 omb_socialButtons">            
        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">	
                <form class="omb_loginForm" action="" autocomplete="off" method="POST">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="username" placeholder="email address">
                    </div>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input  type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Login</button>
                </form>
                <p class="text-danger">${mess}</p>
            </div>
        </div>
        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-3">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me">Remember Me
                </label>
            </div>
            <div  class="col-xs-12 col-sm-3">
				<p class="omb_forgotPwd">
					<a style="color: black" href="#">Forgot password?</a>
				</p>
			</div>
        </div>	    	
    </div>



</div>