
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="adminSidebar.jsp"></jsp:include>

<center>  
       <h2><span style="color:#a442f4">Add Category</span></h2>
</center>

<script type="text/javascript" src='tinymce/js/tinymce/jquery.tinymce.min.js'></script>
<script type="text/javascript" src='tinymce/js/tinymce/tinymce.js'></script>
<script type="text/javascript" src='tinymce/js/tinymce/tinymce.min.js'></script>
<script type="text/javascript" src='tinymce\src\plugins\autoresize\src\main\js\Plugin.js'></script>
  <script type="text/javascript">
  tinymce.init({
    selector: '#myTextarea',
    theme: 'modern',
    //width: 600,
    //height: 200,
    plugins: "autoresize",
    
    plugins: [
      'advlist anchor autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
      'searchreplace wordcount visualblocks visualchars code fullscreen fullpage colorpicker insertdatetime media nonbreaking',
      'save table contextmenu directionality emoticons template paste textcolor textpattern imagetools'
    ],
    content_css: 'css/content.css',
    toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons'
  });
</script> 

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
                            <h2>COVID-19 INFORMATION & RESOURCE INDIA</h2>
                            
                        </div>
                        <div class="body1">
   <style>
		iframe {
			width: 100%;
			height: 500px;
		}
		.card1 .body1 {
    font-size: 14px;
    color: #555;
    
}
	</style>                   
<iframe src="https://docs.google.com/spreadsheets/d/e/2PACX-1vSc_2y5N0I67wDU38DjDh35IZSIS30rQf7_NYZhtYYGU1jJYT6_kDx4YpF-qw0LSlGsBYP8pqM_a1Pd/pubhtml?urp=gmail_link#" name="myFrame" id="myIframe">Foo</iframe>
    <!-- <p><a href="https://www.tutorialrepublic.com" target="myFrame">Open TutorialRepublic.com</a></p> -->


                            
                            
                        </div>
                    </div>
                </div>
            </div>
            
            <button onclick="getLocation()">Try It</button>

<p id="demo"></p>
<div id="address"></div>

 
    



<script>
var x = document.getElementById("demo");
var addrDiv = document.getElementById("address");
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
  x.innerHTML = "Latitude: " + position.coords.latitude + 
  "<br>Longitude: " + position.coords.longitude;
  
  var geocoder = new google.maps.Geocoder(); 
            var point = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            geocoder.geocode({ 'latLng': point }, function (results, status) 
            { 
                // This is checking to see if the Geocode Status is OK before proceeding
                if (status == google.maps.GeocoderStatus.OK) 
                {
                    console.log(results);
                    var formatted_address = (results[0].formatted_address); 
                    addrDiv.innerHTML = formatted_address;
                }
                else
                {
                    addrDiv.innerHTML = "failed to get address";
                }
            });
}



/* window.onload=function(){
	var iframe = document.getElementById('myIframe');
	var html = '<body>Foo</body>';
	iframe.src = 'https://docs.google.com/spreadsheets/d/e/2PACX-1vSc_2y5N0I67wDU38DjDh35IZSIS30rQf7_NYZhtYYGU1jJYT6_kDx4YpF-qw0LSlGsBYP8pqM_a1Pd/pubhtml?urp=gmail_link#'+'data:text/html;charset=utf-8,' + encodeURI(html);
	document.body.appendChild(iframe);
	console.log('iframe.contentWindow =', iframe.contentWindow);
} */

         
         
</script>
 
 <!-- <script>
 $(document).ready(function(){
 var iframes = jQuery('iframe');

 $.each(iframes, function(i, iframe){
//this gives access to the window object of iframe
  childWindow = iframe.contentWindow;
   //now fire your selector query here
  alert(childWindow.document.querySelector("div a").href); 
//or console.log(childWindow document.querySelector("div a").getAttribute("href"));
 })
 });
 </script> -->      
<jsp:include page="adminFooter.jsp"></jsp:include>
