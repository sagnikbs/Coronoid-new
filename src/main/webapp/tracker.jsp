<%@ page import="com.codoid.products.exception.FilloException" %>
<%@ page import="com.codoid.products.fillo.Connection" %>
<%@ page import="com.codoid.products.fillo.Fillo" %>
<%@ page import="com.codoid.products.fillo.Recordset" %>

<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="sidebar.jsp"></jsp:include>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<center>  
       <h2><span style="color:#a442f4">Tracker</span></h2>
</center>


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
            
            
        <section class="content">
        <div class="container-fluid">
            
            <!-- Basic Validation -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card1">
                        <div class="header">
                            <h2>TRACKER</h2>
                            
                        </div>
                        <div class="body1">
  <style>
  .card1 .body1 {
    font-size: 14px;
    color: #555;
  </style> 
  
  
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>                       
  <div id="map" style="width: 100%; height: 500px;"></div>
<!-- Replace the value of the key parameter with your own API key. -->
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdI1PF2CKuAwNItkEiUj4H3wofQelimOE&callback=initMap">
</script>

<%

   String flightPlanCoordinates=session.getAttribute("current").toString();
   String center=session.getAttribute("lastcurrentposition").toString();

   System.out.println("center"+center);
   System.out.println("flightPlanCoordinates"+flightPlanCoordinates);
   
   

%>


		<script>
  function initMap() {
	  var map = new google.maps.Map(document.getElementById('map'), {
	    zoom: 11,
	    center: <%=center%>,
	    mapTypeId: 'roadmap'
	  });
      var center=<%=center%>;
     
	  var flightPlanCoordinates = <%=flightPlanCoordinates%>;
	  
	 
	  var flightPath = new google.maps.Polyline({
	    path: flightPlanCoordinates,
	    geodesic: true,
	    strokeColor: '#FF0000',
	    strokeOpacity: 1.0,
	    strokeWeight: 2
	  });

	  
	  flightPath.setMap(map);
	  
	  <%-- var marker = new google.maps.Marker({
		    // The below line is equivalent to writing:
		    // position: new google.maps.LatLng(-34.397, 150.644)
		    position: <%=firstMarker%>,
		    map: map
		  });
		  
		  
		  var marker1 = new google.maps.Marker({
		    // The below line is equivalent to writing:
		    // position: new google.maps.LatLng(-34.397, 150.644)
		    position: <%=lastMarker%>,
		    map: map
		  }); --%>
		  
	}

</script>

		
		
		
  

                        </div>
                    </div>
                </div>
            </div>
  
 <br><br>      
        
<jsp:include page="adminFooter.jsp"></jsp:include>
