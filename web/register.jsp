<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="css/main.css" rel="stylesheet" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/styles.css" rel="stylesheet" />   
<link href="css/main.css" rel="stylesheet" />   
<div class="container register">
    <div class="row">
        <div class="col-md-3 register-left">
            <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt=""/>
            <h3>Welcome</h3>
            <p>You are 30 seconds away from earning your own money!</p>
            <a href="login" class="btn btn-success btn-lg" role="button" aria-pressed="true">Login</a>
        </div>
        <div class="col-md-9 register-right">          
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3 class="register-heading">Register</h3>
                    <form action="login?do=Register" method="post">
                        <div class="row register-form">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${username != null}">
                                            <input type="text" class="form-control" placeholder="User Name *" value="${username}" name="username" />                                     
                                        </c:when> <c:otherwise>
                                            <input type="text" class="form-control" placeholder="User Name *" value="" name="username" />      
                                        </c:otherwise>                                       
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${password != null}">                                        
                                            <input type="password" class="form-control" placeholder="Password *" value="${password}" name="password" />
                                        </c:when><c:otherwise>
                                            <input type="password" class="form-control" placeholder="Password *" value="" name="password" />      
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${displayname != null}">                                    
                                            <input type="text" class="form-control" placeholder="DisplayName *" value="${displayname}" name="displayname" />
                                        </c:when><c:otherwise>
                                            <input type="text" class="form-control" placeholder="DisplayName *" value="" name="displayname" />    
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group"> 
                                    <c:choose>
                                        <c:when test="${address != null}">
                                            <input type="text" class="form-control" placeholder="Address *" value="${address}" name="address" /> 
                                        </c:when><c:otherwise>
                                            <input type="text" class="form-control" placeholder="Address *" value="" name="address" />   
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${email != null}">                                    
                                            <input type="text" class="form-control" placeholder="Email *" value="${email}" name="email" />
                                        </c:when><c:otherwise>
                                            <input type="text" class="form-control" placeholder="Email *" value="" name="email" />     
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">    
                                    <c:choose>
                                        <c:when test="${phone != null}">
                                            <input type="text" class="form-control" placeholder="Phone *" value="${phone}" name="phone" />
                                        </c:when><c:otherwise>
                                            <input type="text" class="form-control" placeholder="Phone *" value="" name="phone" /> 
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="form-group">  
                                    <c:choose>
                                        <c:when test="${imageURL != null}">
                                            <input type="text" class="form-control" placeholder="ImageURL *" value="${imageURL}" name="imageURL" />
                                        </c:when><c:otherwise>
                                            <input type="text" class="form-control" placeholder="ImageURL *" value="" name="imageURL" />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${mess != null}"> ${mess}</c:if>
                                <input type="submit" name="submit" class="btnRegister"  value="Register"/>
                            </div>
                        </div>
                    </form>
                </div>               
            </div>
        </div>
    </div>

</div>