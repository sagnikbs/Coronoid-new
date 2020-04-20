<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.*" %>
<jsp:include page="adminHeader.jsp"></jsp:include>

<body class="login-page">
    <div class="login-box">
        <div class="logo">
            <a href="javascript:void(0);">CORO<b>NOID</b></a>
            <small>Login</small>
        </div>
        <div class="card">
            <div class="body">
                <form id="sign_in" method="POST" action="welcome_to_dashboard">
                    <div class="msg">Sign in to start your session</div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                        <div class="form-line">
                            <input type="email" class="form-control" name="username" placeholder="Enter your Email" required autofocus>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                        <div class="form-line">
                            <input type="number" class="form-control" name="password" placeholder="Enter your OTP" required>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-xs-8 p-t-5">
                            <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                            <label for="rememberme">Remember Me</label>
                        </div>
                        <div class="col-xs-4">
                            <button class="btn btn-block bg-pink waves-effect" type="submit">SIGN IN</button>
                        </div>
                    </div>
                    <div class="row m-t-15 m-b--20">
                        <div class="col-xs-6">
                            <a href="<%=request.getContextPath()%>/register">Register Now!</a>
                        </div>
                        <div class="col-xs-6 align-right">
                            <a href="<%=request.getContextPath()%>/updateUser">Forgot Password?</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    

<jsp:include page="adminFooter.jsp"></jsp:include>