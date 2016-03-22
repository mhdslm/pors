<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-ng-app="Pors">
<head>

 
 <link rel="icon" type="image" href="${ pageContext.request.contextPath}/project/app/images/rsz_1pdas2_converted.png">
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Doctor Appointment System</title>
<!-- <link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet"> -->

	<script src="${ pageContext.request.contextPath }/project/app/scripts/jquery1.min.js"
		type="text/javascript"></script> 
		
<link rel="stylesheet"
	href="${ pageContext.request.contextPath}/project/app/scripts/bootstrap.min.css">
	
<link rel="stylesheet"
	href="${ pageContext.request.contextPath}/project/app/scripts/small-business.css">	

<style type="text/css"> 

 * {
	font-family: Helvetica;
}

h1 {
	font-family: Helvetica;
}  
	.accepted{background-color: #64E986;}
	.rejected{background-color: #FF2400;}
	.pending{background-color: #FFDB58;}
	.btn-primary:active, .btn-primary.active {
    	background: #007299;
    	box-shadow: none;
}
</style>

</head>
<body class="container">

<nav class="navbar navbar-default navbar-fixed-top" role="navigation" ng-controller="NavBarCtrl as nav">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" ui-sref="frontpage">
                    <img class="img-responsive" src="${ pageContext.request.contextPath}/project/app/images/rsz_1pdas2.png" alt="">
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            
           <!--  <div id="navbar">
				<ul class="nav navbar-nav">
					<li><a ui-sref="login">Login</a></li>
					<li><a ui-sref="register">Register</a></li>
				</ul>
			</div> -->
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right" >
					
					<li ng-hide="nav.curUser.authenticated"
						ng-class="{ active: nav.isActive('/register')}"><a
						ui-sref="register">Register <span class="glyphicon glyphicon-user "></span></a></li>
						
						<li
						ng-hide="nav.curUser.authenticated"
						ng-class="{ active: nav.isActive('/login')}"><a
						ui-sref="login"> Login <span class="glyphicon glyphicon-log-in "></span></a></li>
					
					</ul>
					<ul class="nav navbar-nav">
					
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/home/patient')}"><a
						ui-sref="home.patient">Home <span class="glyphicon glyphicon-home "></span></a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/myDoctors')}"><a
						ui-sref="myDoctors" ui-sref-opts="{reload: true}">Appointment Portal <span class="glyphicon glyphicon-list-alt"></span></a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/doctorRegister')}"><a
						ui-sref="doctorRegister">Doctor Catalog <span class="glyphicon glyphicon-folder-open"></span></a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/appointmentHistory')}"><a
						ui-sref="appointmentHistory">Appointment History <span class="glyphicon glyphicon-time"></span></a></li>
						
					
				
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==2"
						ng-class="{ active: nav.isActive('/home/doctor')}"><a
						ui-sref="home.doctor">Home</a></li>
						
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==2"
						ng-class="{ active: nav.isActive('/showDocApps')}"><a
						ui-sref="showDocApps">View All Appointments</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						
					<li ng-show="nav.curUser.authenticated" ng-click="nav.logout()"><a
						ui-sref="login">Logout <span class="glyphicon glyphicon-log-out "></span></a>
				</ul>
			</div>
<!--                 <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul> -->
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

	<!-- <nav class="navbar navbar-default" ng-controller="NavBarCtrl as nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">PORS</a>
			</div>
			<div id="navbar">
				<ul class="nav navbar-nav">
					<li><a ui-sref="login">Login</a></li>
					<li><a ui-sref="register">Register</a></li>
				</ul>
			</div>
			<div id="navbar">
				<ul class="nav navbar-nav" >
					<li
						ng-hide="nav.curUser.authenticated"
						ng-class="{ active: nav.isActive('/login')}"><a
						ui-sref="login">Login</a></li>
					<li ng-hide="nav.curUser.authenticated"
						ng-class="{ active: nav.isActive('/register')}"><a
						ui-sref="register">Register</a></li>
					Patient options
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/home/patient')}"><a
						ui-sref="home.patient">Home</a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/myDoctors')}"><a
						ui-sref="myDoctors">Schedule Appointment</a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/doctorRegister')}"><a
						ui-sref="doctorRegister">Register with Doctor</a></li>
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==1"
						ng-class="{ active: nav.isActive('/appointmentHistory')}"><a
						ui-sref="appointmentHistory">View Appointment History</a></li>
						
					Doctor options
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==2"
						ng-class="{ active: nav.isActive('/home/doctor')}"><a
						ui-sref="home.doctor">Home</a></li>
						
					<li 
						ng-show="nav.curUser.authenticated && nav.curUser.roleId==2"
						ng-class="{ active: nav.isActive('/showDocApps')}"><a
						ui-sref="showDocApps">View All Appointments</a></li>
						
					<li ng-show="nav.curUser.authenticated" ng-click="nav.logout()"><a
						ui-sref="login">Logout</a>
				</ul>
			</div>
		</div>
	</nav> -->
	<div class="container-fluid">
		<div class="row">
			<div class="span12">
				<div ui-view></div>
			</div>
		</div>
	</div>

	<!-- Angular -->
	<!-- <script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.js"></script> -->
		
	<script	src="${ pageContext.request.contextPath }/project/app/scripts/angular.min.js"></script>
	<!-- UI-Router -->
	<!-- <script
		src="//angular-ui.github.io/ui-router/release/angular-ui-router.js"></script> -->
		
		<script src="${ pageContext.request.contextPath }/project/app/scripts/angular-ui-router.js"></script>
		
		
	<!-- App Script -->
	<script src="${ pageContext.request.contextPath }/project/app/app.js"
		type="text/javascript"></script>
		
		
	 
	
	  <script src="${ pageContext.request.contextPath }/project/app/scripts/bootstrap.min.js"
		type="text/javascript"></script> 
		
		
	 	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
 <!--  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> --> 

</body>
</html>