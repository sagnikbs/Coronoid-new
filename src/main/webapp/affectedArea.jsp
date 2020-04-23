
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="adminSidebar.jsp"></jsp:include>
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
  
  String allCurrentPosition=session.getAttribute("currentPositionList1").toString();
  
  String currentPositionByEmail=session.getAttribute("currentPositionByEmail1").toString();
  
  %>
 
  
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>                       
  <div id="map" style="width: 100%; height: 500px;"></div>
<!-- Replace the value of the key parameter with your own API key. -->
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdI1PF2CKuAwNItkEiUj4H3wofQelimOE&callback=initMap">
</script>
  
  
  
  
  
  
 
 

                        </div>
                    </div>
                </div>
            </div>

 <br><br>    
 
 <script>
 function getLocation() {
	  if (navigator.geolocation) {
		    
	    navigator.geolocation.getCurrentPosition(showPosition);
	  } else { 
	    x.innerHTML = "Geolocation is not supported by this browser.";
	  }
	}

	function showPosition(position) {
		position.coords.latitude;
		position.coords.longitude;
		
		var cp="{lat: "+position.coords.latitude+","+"lng: "+position.coords.longitude+"}"
		
		
		
		
		
		
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
	
</script>	


<script>
	function initMap() {

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 14,
          center: <%=currentPositionByEmail%>
        });
        
        var marker = new google.maps.Marker({
            position: <%=currentPositionByEmail%>,
            map: map,
            title: 'You Are Here',
            icon: {
                url: "https://maps.google.com/mapfiles/ms/icons/blue-dot.png"
              }
          });
        var infowindow = new google.maps.InfoWindow({
            content: 'You Are Here'
          });

        marker.addListener('click', function() {
            infowindow.open(map, marker);
          });
        // Create an array of alphabetical characters used to label the markers.
        var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        // Add some markers to the map.
        // Note: The code uses the JavaScript Array.prototype.map() method to
        // create an array of markers based on a given "locations" array.
        // The map() method here has nothing to do with the Google Maps API.
        var markers = locations.map(function(locations, i) {
          return new google.maps.Marker({
            position: locations,
            label: labels[i % labels.length],
            draggable:false,
          });
        });

        
        var str_array = [<%=allCurrentPosition%>];
       

        for(var i = 0; i < str_array.length; i++) {
           // Trim the excess whitespace.
           
           // Add additional code here, such as:
           //alert(str_array[i]);
        var wellCircle = locations.map(function(locations, i) {
          return new google.maps.Circle({
        	  strokeColor: '#FF0000',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              fillColor: '#FF0000',
              fillOpacity: 0.35,
              draggable: false,
              map: map,
              center: new google.maps.LatLng(str_array[i]),
              radius: 500
          });
          
          /* wellCircle.bindTo('center', 'position');
          wellCircle.draggable=false; */
          
        });
         
        
           
          /*  var wellCircle = new google.maps.Circle({
               strokeColor: '#FF0000',
               strokeOpacity: 0.8,
               strokeWeight: 2,
               fillColor: '#FF0000',
               fillOpacity: 0.35,
               map: map,
               center: new google.maps.LatLng(str_array[i]),
               radius: 500
           }); */
           
        }
        
        /* google.maps.event.addListener(wellCircle, 'dragend', function(event) {
            var lng= event.latLng.lng();
            var lat= event.latLng.lat();
            
          }); */
        
        
        
          
        // Add a marker clusterer to manage the markers.
        /* var markerCluster = new MarkerClusterer(wellCircle, map,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
         */
	}
      var locations = [
        
    	  <%=allCurrentPosition%>
      ]
 </script>  
        
<jsp:include page="adminFooter.jsp"></jsp:include>
