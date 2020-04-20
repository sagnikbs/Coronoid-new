<%@ page import="java.time.LocalDateTime" %> 
<%@ page import="java.time.format.DateTimeFormatter" %>
<body class="theme-blue">
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


<section>
        <!-- Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <!-- User Info -->
            <div class="user-info">
                
                <div class="info-container">
                <%
                   String email=session.getAttribute("username").toString();
                   int at = email.indexOf('@');
                   String username = email.substring(0, at);
                   session.setAttribute("usernamenew", username);
                %>
                
                    <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>Welcome:</span> <%=session.getAttribute("usernamenew")%></div>
                    
                    <div class="btn-group user-helper-dropdown">
                        <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                        <ul class="dropdown-menu pull-right">
                            
                            <li><a href="<%=request.getContextPath()%>/logout"><i class="material-icons">input</i>Sign Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>

<!-- Menu -->
            <div class="menu">
                <ul class="list">
                    <li class="header">
                       <a href="<%=request.getContextPath()%>/adminDashboard">
                        <i class="material-icons">dashboard</i>
                        <span>DASHBOARD</span>
                       </a>
                    
                    </li>
                    
                  <script type="text/javascript">
                  $(document).ready(function(){
                	    $("#mdModal").modal('show');
                	    var userStatus="";
                	    var status;
                		if(userStatus=="0")
                		{
                			
                			$('li.edit').hide();
            		}
                		else
                		{
                			$('li.edit').show();
                		}
                	});
                <%-- window.onload = function(){
                	
                	
                	
                	var userStatus="<%=userStatus%>";
                	
                	if(userStatus=="0"){
                		
                		document.getElementById("edit").children[3].style.display = "none";
                	}
                	else{
                		/* document.getElementById("mdModal").style.visibility = "visible"; */
                		console.log("success");
                	}
                } --%>
                </script>     
                    
                <li>
                    <a href="<%=request.getContextPath()%>/highFever">
                        <i class="material-icons">add_to_queue</i>
                        <span>Persons With High Fever</span>
                    </a>
                </li>
                
                <li>
                    <a href="<%=request.getContextPath()%>/symptomsPositive">
                        <i class="material-icons">add_to_queue</i>
                        <span>Persons With Symptoms Positive</span>
                    </a>
                </li>
                <%-- <li>
                    <a href="<%=request.getContextPath()%>/affectedAreaMap">
                        <i class="material-icons">map</i>
                        <span>Affected Area Map</span>
                    </a>
                </li> --%>
                
             </ul>
            </div>
            <!-- #Menu -->
            <!-- Footer -->
            <div class="legal">
                <div class="copyright">
                    &copy; 2020<a href="javascript:void(0);">Coronoid</a>.
                </div>
                <div class="version">
                    <b>Version: </b> 1.0.5
                </div>
            </div>
            <!-- #Footer -->
        </aside>
        <!-- #END# Left Sidebar -->

</section>






