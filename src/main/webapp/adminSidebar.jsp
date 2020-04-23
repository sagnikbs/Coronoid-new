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
                <ul class="list" id="edit">
                    <li class="header">
                       <a href="<%=request.getContextPath()%>/dashboardnew">
                        <i class="material-icons">dashboard</i>
                        <span>DASHBOARD</span>
                       </a>
                    
                    </li>
                    
                <li>
                    <a href="<%=request.getContextPath()%>/addBodyTemperatureAndDetails">
                        <i class="material-icons">add_to_queue</i>
                        <span>Add Body Temperature</span>
                    </a>
                </li>
                <%
                String userStatus=null;
                String datetime=session.getAttribute("datetime").toString();
                /* String datetime1 = datetime.substring(datetime.indexOf("[")+1, datetime.indexOf("]")); */
                String datetimenew=session.getAttribute("futureDate").toString();
                /* String datetimenew1 = datetimenew.substring(datetime.indexOf("[")+1, datetimenew.indexOf("]")); */
                System.out.println("dt"+datetime);
                System.out.println("dtn"+datetimenew);
                
                	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
                    
        			LocalDateTime currentDate = LocalDateTime.now();     			
        			LocalDateTime previousDate=LocalDateTime.parse(datetime,dtf);
        			LocalDateTime futureDate=LocalDateTime.parse(datetimenew,dtf);
        			
        			
        			if(previousDate.isBefore(currentDate) && futureDate.isBefore(currentDate))
        			{
        				userStatus="0";
        				System.out.println("Case 1");
        			}
        			else if(previousDate.isBefore(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        				System.out.println("Case 2");
        			}
        			else if(previousDate.equals(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        				System.out.println("Case 3");
        			}
        			else if(previousDate.isAfter(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        				System.out.println("Case 4");
        			}
        			else if(previousDate.equals(null) && futureDate.equals(null) && !currentDate.equals(null))
        			{
        				userStatus="0";
        				System.out.println("Case 5");
        			}
        			else if(previousDate.equals(currentDate) && futureDate.equals(currentDate))
        			{
        				userStatus="0";
        				System.out.println("Case 6");
        			}
        			else
        			{
        				userStatus="0";
        				System.out.println("Case 7");
        			}
        			System.out.println("status"+userStatus);
        			           /* 1 < 10 && 7 < 10  ----expire
        			           5 < 10 && 12 > 10  ----notexpire 
        			           5 = 5 && 12 > 5 -----notexpire
        			           5 > 1 && 12 > 1 -----timemachine---notexpire---heaven
        			           */
                
        			           
        			           
                
                %>
                  <script type="text/javascript">
                  $(document).ready(function(){
                	    $("#mdModal").modal('show');
                	    var userStatus="<%=userStatus%>";
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
                <li class="edit">
                    <a href="<%=request.getContextPath()%>/affectedArea">
                        <i class="material-icons">map</i>
                        <span>Affected Area Map</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/covid-19_info_resource">
                        <i class="material-icons">info</i>
                        <span>COVID-19 Information & Resource India</span>
                    </a>
                </li>
                
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






