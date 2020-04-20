<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.*" %>
<%@ page import="com.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<jsp:include page="adminHeader.jsp"></jsp:include>

<body class="login-page">
    <div class="login-box">
        <div class="logo">
            <a href="javascript:void(0);">CORO<b>NOID</b></a>
            <small>Register</small>
        </div>
        <div class="card">
            <div class="body">
                <form id="sign_in" method="POST" action="generateOTP">
                    <div class="msg">Register Now!</div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                        <div class="form-line">
                            <input type="email" class="form-control" name="username" placeholder="Enter your Email"  required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                        <div class="form-line">
                            <input type="text" class="form-control" name="name" placeholder="Enter your Name"  required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">phone_iphone</i>
                        </span>
                        <div class="form-line">
                            <input type="text" oninput="numberOnly(this.id);" maxlength="10"  id="phone" class="form-control" name="phone" placeholder="Enter your 10-digit Phone Number" required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                        <div class="form-line">
                            <input type="number" class="form-control" name="password" placeholder="Generate OTP"  required readonly>
                            
                        </div>
                    </div>
                    <input style="display:none" type="text" class="form-control" name="role" placeholder="Name" value="ROLE_USER" required autofocus>
                    <input style="display:none" type="text" class="form-control" name="enabled" placeholder="Name" value="true" required autofocus>
                    <div class="row">
                        <!-- <div class="col-xs-8 p-t-5">
                            <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                            <label for="rememberme">Remember Me</label>
                        </div> -->
                        <div class="col-xs-12">
                            <button class="btn btn-block bg-pink waves-effect" type="submit">Register/Generate OTP</button>
                        </div>
                    </div>
                    <div class="row m-t-15 m-b--20">
                        <div class="col-xs-6">
                            <a href="<%=request.getContextPath()%>/">Login Now!</a>
                        </div>
                        <!-- <div class="col-xs-6 align-right">
                            <a href="forgot-password.html">Forgot Password?</a>
                        </div> -->
                    </div>
                </form>
            </div>
        </div>
    </div>

 <script>
  
 function numberOnly(id) {
	    // Get element by id which passed as parameter within HTML element event
	    var element = document.getElementById(id);
	    // Use numbers only pattern, from 0 to 9
	    var regex = /[^0-9]/gi;
	    // This removes any other character but numbers as entered by user
	    element.value = element.value.replace(regex, "");
	}
 </script>   

<jsp:include page="adminFooter.jsp"></jsp:include>