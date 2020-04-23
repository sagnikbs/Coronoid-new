
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="sidebar.jsp"></jsp:include>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<center>  
       <h2><span style="color:#a442f4">Affected Area Map</span></h2>
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
                    <div class="card1">
                        <div class="header">
                            <h2>AFFECTED AREA MAP</h2>
                            
                        </div>
                        <div class="body1">
  <style>
  .card1 .body1 {
    font-size: 14px;
    color: #555;
  </style> 
  
  <%
  String cluster=session.getAttribute("clusterList").toString();
  System.out.println("Cluster"+cluster);
  
  %>
 
  
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>                       
  <div id="map" style="width: 100%; height: 500px;"></div>
<!-- Replace the value of the key parameter with your own API key. -->
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdI1PF2CKuAwNItkEiUj4H3wofQelimOE&callback=initMap">
</script>
  
  <script>
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
  
  
  
  function initMap() {

	  var map = new google.maps.Map(document.getElementById('map'), {
	    zoom: 3,
	    center: {lat: 22.6396522,lng: 88.40799489999999}
	  });
      
	  // Create an array of alphabetical characters used to label the markers.
	  var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

	  // Add some markers to the map.
	  // Note: The code uses the JavaScript Array.prototype.map() method to
	  // create an array of markers based on a given "locations" array.
	  // The map() method here has nothing to do with the Google Maps API.
	  var markers = locations.map(function(location, i) {
	    return new google.maps.Marker({
	      position: locations,
	      label: labels[i % labels.length]
	    });
	  });

	  // Add a marker clusterer to manage the markers.
	  var markerCluster = new MarkerClusterer(map, markers,
	      {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
	}
  var locations = <%=cluster%>
  
  </script>
 

                        </div>
                    </div>
                </div>
            </div>

 <br><br>      
        
<jsp:include page="adminFooter.jsp"></jsp:include>
