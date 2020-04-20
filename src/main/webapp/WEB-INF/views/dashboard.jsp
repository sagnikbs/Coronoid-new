<%@ page import="java.time.LocalDateTime" %> 
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="adminSidebar.jsp"></jsp:include>

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


            
            <!-- Basic Validation -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>ADD BODY TEMPERATURE AND PROVIDE ADDITIONAL DETAILS</h2>
                            
                        </div>
                        <div class="body">
                      
<!--  <div class="button-demo js-modal-buttons">
    <button type="button" data-color="purple" class="btn bg-purple waves-effect" data-toggle="modal" data-target="#mdModal">PURPLE</button>
    </div>   -->  
    

<form action="addBodyTemperature" method="POST">
                                
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        <label>YOUR BODY TEMPERATURE IN (°F)</label>
                                        <input type="number" step="0.01" class="form-control" placeholder="Enter your body temperature" name="temp" id="temp" oninput="myTemp()"/>
                                    </div>
                                </div>
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        <label>DO YOU HAVE FEVER ?</label>
                                        <input type="text" class="form-control" name="fever" id="fever" readonly/>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>DO YOU HAVE COUGH ?</label>
                                    <select class="form-control show-tick" name="cough">
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>DO YOU HAVE TIREDNESS ?</label>
                                    <select class="form-control show-tick" name="tiredness">
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>DO YOU HAVE DIFFICULTY OF BREATHING ?</label>
                                    <select class="form-control show-tick" name="breathingProb">
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>TRAVEL WITHIN 10 DAYS</label>
                                    <select class="form-control show-tick" name="travelWithin10Days" required>
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div style="display:none">
                                <input style="display:none" type="text" class="form-control" placeholder="" name="loginId"/>
                                <input id="lat" type="text" class="form-control" placeholder="" name="lat"/>
                                <input id="lng" type="text" class="form-control" placeholder="" name="lng"/>
                                <input style="display:none" type="datetime-local" class="form-control" placeholder="" name="datetime"/>
                                <input style="display:none" type="text" class="form-control" placeholder="" name="address" id="address"/>
                                <input style="display:none" type="text" class="form-control" placeholder="" name="symptoms"/>
                                </div>
                                
                                <input type="submit" class="btn btn-primary waves-effect" value="Add Temperature And Details"/>
                            </form>
                            
                               
                        </div>
                    </div>
                </div>
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
        			}
        			else if(previousDate.isBefore(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        			}
        			else if(previousDate.equals(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        			}
        			else if(previousDate.isAfter(currentDate) && futureDate.isAfter(currentDate))
        			{
        				userStatus="1";
        			}
        			else if(previousDate.equals(null) && futureDate.equals(null) && !currentDate.equals(null))
        			{
        				userStatus="0";
        			}
        			else if(previousDate.equals(currentDate) && futureDate.equals(currentDate))
        			{
        				userStatus="0";
        			}
        			else
        			{
        				userStatus="0";
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
	if(userStatus=="0"){
		
		status="Sorry you have not enabled Affected Area Map. Please add your body temperature and additional details to enable Affected Area Map.";
		$("p").html(status);
	}
	else{
		status="You have enabled Affected Area Map. Your validity expire on <%=datetimenew%>.";
		$("p").html(status);
		/* document.getElementById("mdModal").style.visibility = "visible"; */
		console.log("success");
	}
});



function myTemp() {
	  var x = document.getElementById("temp").value;
	  
	  if(x > 98.4){
		
		  document.getElementById("fever").value="YES";
		  
	  }
	  else{
		  
		  document.getElementById("fever").value="NO";
	  }
	  
	  

}


var x = document.getElementById("demo");

function getLocation() {
  if (navigator.geolocation) {
	    
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
	document.getElementById('lat').value = position.coords.latitude;
	document.getElementById('lng').value = position.coords.longitude;
	
	var cp="{lat: "+position.coords.latitude+","+"lng: "+position.coords.longitude+"}"
	
	document.getElementById('currentPosition').value = cp;
	
	
	
	
	/* var xhr = new XMLHttpRequest();
    xhr.open("POST", "welcome_to_dashboard", true); */
	
	var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    // This is making the Geocode request
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'latLng': latlng }, function (results, status) {
        if (status !== google.maps.GeocoderStatus.OK) {
            /* alert(status); */
        }
        // This is checking to see if the Geoeode Status is OK before proceeding
        if (status == google.maps.GeocoderStatus.OK) {
            console.log(results);
            var address = (results[0].formatted_address);
            document.getElementById('address').value=results[0].formatted_address;
            
            
        }
    });
	}
	


/* function current() {
	document.forms['current'].submit();
	} */
</script> 
<script src="https://maps.google.com/maps/api/js?key=AIzaSyBdI1PF2CKuAwNItkEiUj4H3wofQelimOE&callback=initMap" type="text/javascript"></script>         
<jsp:include page="adminFooter.jsp"></jsp:include>
