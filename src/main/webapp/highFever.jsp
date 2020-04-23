
<jsp:include page="adminHeader.jsp"></jsp:include>

<jsp:include page="sidebar.jsp"></jsp:include>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<center>  
       <h2><span style="color:#a442f4">Add Body Temperature</span></h2>
</center>

<script type="text/javascript" src='tinymce/js/tinymce/jquery.tinymce.min.js'></script>
<script type="text/javascript" src='tinymce/js/tinymce/tinymce.js'></script>
<script type="text/javascript" src='tinymce/js/tinymce/tinymce.min.js'></script>
<script type="text/javascript" src='tinymce\src\plugins\autoresize\src\main\js\Plugin.js'></script>
  

<body onload="getLocation()" class="theme-blue">


<style>
.container-fluid1 {
    /* padding-right: 15px; */
    /* padding-left: 15px; */
    margin-right: auto;
    margin-left: auto;
</style>
    <!-- Page Loader -->
    
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid1">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                <a href="javascript:void(0);" class="bars"></a>
                <a class="navbar-brand">CORONOID</a>
            </div>
            </div>
            </nav>
            
            
        <section class="content">
        
            
            
                      
        <div class="container-fluid1">
        <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                PERSONS WITH HIGH FEVER
                            </h2>
                          <ul class="header-dropdown m-r--5">
                                <li class="dropdown">
                                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">more_vert</i>
                                    </a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:void(0);">Action</a></li>
                                        <li><a href="javascript:void(0);">Another action</a></li>
                                        <li><a href="javascript:void(0);">Something else here</a></li>
                                    </ul>
                                </li>
                            </ul>  
                        </div>
                        <div class="body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped table-hover dataTable js-exportable">
                                    <thead>
                                      <tr>
                                        <th>SLNO</th>
                                    	<th>Name</th>
                                    	<th>Email</th>
                                    	<th>Phone</th>
                                    	<th>Temperature</th>
                                    	<th></th>
                                      </tr>	
                                    </thead>
                                    <tfoot>
                                      <tr>
                                        <th>SLNO</th>
                                    	<th>Name</th>
                                    	<th>Email</th>
                                    	<th>Phone</th>
                                    	<th>Temperature</th>
                                    	<th></th>
                                      </tr>	
                                    </tfoot>
                                 
                                  
                                    <tbody>
                                    <c:forEach items="${highFever}" var="fever" varStatus="loop">
                                        <tr>
                                        	<td>${loop.index+1}</td>
                                        	<td>${fever.name}</td>
                                        	<td>${fever.loginId}</td>
                                        	<td>${fever.phone}</td>
                                        	<td>${fever.temp}</td>
                                            <td><a href="<c:url value="trackPerson/${fever.loginId}"/>"><button class="btn btn-success waves-effect">Track</button></a></td>
                                        </tr>
                                 </c:forEach>
                                    </tbody>
                                    
                                </table>

                            </div>
                        </div>
                    </div>


                    


                </div>
            </div>
        </div>  
    
                            
                       

         
<jsp:include page="adminFooter.jsp"></jsp:include>
