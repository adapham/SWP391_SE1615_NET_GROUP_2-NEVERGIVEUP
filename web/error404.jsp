<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>404 HTML Template by Colorlib</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style1.css" />

    </head>

    <body>

        <div id="notfound">
            <div class="notfound">
                <div class="notfound-404">
                    <h1>4<span></span>4</h1>
                </div>
                <h2>Oops! Page Not Be Found</h2>
                <p></p>
                <c:if test="${sessionScope.Account ==null}">
                   <a href="home?do=home&fresh=1">Back to homepage </a>
                </c:if>

                <c:if test="${sessionScope.Account !=null}">
                   <a href="home">Back to homepage </a>
                </c:if>
            </div>
        </div>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
