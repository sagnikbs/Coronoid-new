
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="adminSidebar.jsp"></jsp:include>

<center>  
       <h2><span style="color:#a442f4">Add Body Temperature</span></h2>
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
            
            <!-- Basic Validation -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>ADD BODY TEMPERATURE AND PROVIDE ADDITIONAL DETAILS</h2>
                            
                        </div>
                        <div class="body">
                      
                    
<form action="addBodyTemperature" method="POST">
                                
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        <label>YOUR BODY TEMPERATURE IN (°F)</label>
                                        <input type="number" step="0.01" class="form-control" placeholder="Enter your body temperature" name="temp" id="temp" oninput="myTemp()" required/>
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
                                    <select class="form-control show-tick" name="cough" required>
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>DO YOU HAVE TIREDNESS ?</label>
                                    <select class="form-control show-tick" name="tiredness" required>
                                        <option value="">-- Please select --</option>
                                        <option value="YES">YES</option>
                                        <option value="NO">NO</option>
                                        
                                    </select>
                                </div>
                                </div>
                                <div class="row clearfix">
                                <div class="col-sm-12">
                                <label>DO YOU HAVE DIFFICULTY OF BREATHING ?</label>
                                    <select class="form-control show-tick" name="breathingProb" required>
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
                                <input type="text" class="form-control" placeholder="" name="loginId"/>
                                <input id="lat" type="text" class="form-control" placeholder="" name="lat"/>
                                <input id="lng" type="text" class="form-control" placeholder="" name="lng"/>
                                <input type="datetime-local" class="form-control" placeholder="" name="datetime"/>
                                <input type="text" class="form-control" placeholder="" name="address" id="address"/>
                                <input type="text" class="form-control" placeholder="" name="symptoms"/>
                                </div>
                                
                                <input type="submit" class="btn btn-primary waves-effect" value="Add Temperature And Details"/>
                            </form>
                            
                            
                        </div>
                    </div>
                </div>
            </div>
            

                
<script type="text/javascript">



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
	
	/* alert("lat"+position.coords.latitude);
	alert("lng"+position.coords.longitude); */
    var cp="{lat: "+position.coords.latitude+","+"lng: "+position.coords.longitude+"}"
	
	document.getElementById('currentPosition').value = cp;
	
	var latlng = new google.maps.LatLng(40.731, -73.997);
	var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'location': latlng }, function (results, status) {
        if (status === 'OK') {
            /* alert("status"+status); */
            var address = results[0].formatted_address;
           /*  alert("address"+address); */
            document.getElementById('address').value=results[0].formatted_address;
            
        }
        else{
        	/* alert("sorry"); */
        }
})
}
	


</script> 
<script async defer src="https://maps.google.com/maps/api/js?key=AIzaSyBdI1PF2CKuAwNItkEiUj4H3wofQelimOE" type="text/javascript"></script>         
<jsp:include page="adminFooter.jsp"></jsp:include>
