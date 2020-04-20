<%@ page import="java.time.LocalDateTime" %> 
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="sidebar.jsp"></jsp:include>

<center>  
       <h2><span style="color:#a442f4">Dashboard</span></h2>
</center>



<body onload="getLocation()" class="theme-blue">
    
    
    <!-- Page Loader -->
    
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                <a href="javascript:void(0);" class="bars"></a>
                <a class="navbar-brand">CORONOID</a>
            </div>
            </div>
            </nav>
            
            
        <section class="content">
        <div class="container-fluid">
            
            <div class="block-header">
                <h2>DASHBOARD</h2>
            </div>

            <!-- Widgets -->
            <div class="row clearfix">
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-pink hover-expand-effect">
                        <div class="icon">
                            <i class="material-icons">playlist_add_check</i>
                        </div>
                        <div class="content">
                            <div class="text">PERSONS WITH HIGH FEVER</div>
                            <br>
                            <div class="number count-to" data-from="0" data-to="<%=session.getAttribute("countHighFever") %>" data-speed="15" data-fresh-interval="20"></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-cyan hover-expand-effect">
                        <div class="icon">
                            <i class="material-icons">playlist_add_check</i>
                        </div>
                        <div class="content">
                            <div class="text">PERSONS WITH SYMPTOMS POSITIVE</div>
                            <div class="number count-to" data-from="0" data-to="<%=session.getAttribute("countSymptomsPositive") %>" data-speed="1000" data-fresh-interval="20"></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box bg-light-green hover-expand-effect">
                        <div class="icon">
                            <i class="material-icons">playlist_add_check</i>
                        </div>
                        <div class="content">
                            <div class="text">PERSONS ADDED BODY TEMPERATURE</div>
                            <div class="number count-to" data-from="0" data-to="<%=session.getAttribute("countAddedTemp") %>" data-speed="1000" data-fresh-interval="20"></div>
                        </div>
                    </div>
                </div>
                
            </div>
            <!-- #END# Widgets -->
                        
                    </div>
                
         

<!-- Default Size -->
     <%--        <div class="modal fade" id="mdModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content modal-col-purple">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Enable Affected Area Map</h4>
                        </div>
                        <div class="modal-body">
                            <h5>Just for your information!!</h5>
                            Hello <%=session.getAttribute("usernamenew")%>. Please add your body temperature and additional details to enable and view "Affected Area Map".
                            Once you add your body temperature and additional details the Affected Area Map will be enabled for 1 week. Again you have to add your body temperature and additional details to enable for another 1 week and goes on.
                            Thank you.
                            
                            <h5>Your Current Status</h5>
                            
                            <p></p>
                            
                        </div>
                        <div class="modal-footer">

<button class="btn btn-link waves-effect" data-dismiss="modal">CLOSE</button> --%>
                
                            
                        </div>
                    </div>
                </div>
            </div>
            
            
            
            
<jsp:include page="adminFooter.jsp"></jsp:include>
