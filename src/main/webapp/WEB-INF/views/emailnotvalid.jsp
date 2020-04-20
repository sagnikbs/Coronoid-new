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
            <small>Access Denied</small>
        </div>
        <div class="card">
            <div class="body">
            
             Your email address is not valid. Unable to send OTP in this email address. Please try with valid email address.
                <%-- <form id="sign_in" method="POST" action="<%=request.getContextPath()%>/updateUser/<%=session.getAttribute("id")%>">
                   <div class="msg">Register Now!</div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                        <div class="form-line">
                            <input type="text" class="form-control" name="username" placeholder="Name" value="<%=session.getAttribute("name") %>" required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                        <div class="form-line">
                            <input type="number" class="form-control" name="password" placeholder="Generated OTP" value="<%=session.getAttribute("password") %>" required readonly>
                            
                        </div>
                    </div>
                    <input style="display:none" type="text" class="form-control" name="role" placeholder="Name" value="ROLE_USER" required autofocus>
                    <input style="display:none" type="text" class="form-control" name="enabled" placeholder="Name" value="true" required autofocus>
                    <div class="row">
                        <div class="col-xs-8 p-t-5">
                            <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                            <label for="rememberme">Remember Me</label>
                        </div>
                        <div class="col-xs-12">
                            <button class="btn btn-block bg-pink waves-effect" type="submit">Re-generate OTP</button>
                        </div>
                    </div> --%>
                    <div class="row m-t-15 m-b--20">
                        <div class="col-xs-6">
                            <a href="<%=request.getContextPath()%>/">Login Now!</a>
                        </div>
                        <!-- <div class="col-xs-6 align-right">
                            <a href="forgot-password.html">Forgot Password?</a>
                        </div> -->
                    </div>
                <!-- </form> -->
            </div>
        </div>
    </div>

    

<jsp:include page="adminFooter.jsp"></jsp:include>