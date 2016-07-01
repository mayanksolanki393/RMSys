<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" data-ng-app="empApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Admin Dashboard</title>

<!-- Bootstrap core CSS -->
<link href="components/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="components/dashboard.css" rel="stylesheet">
<link href="app/admin/css/MainStyle.css" rel="stylesheet">
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#/">Requirment Management System <sup
				class="label label-danger">Admin</sup></a>
		</div>
		<ul class="nav navbar-nav navbar-right" style="margin-right:4px;">
			<li><a href="#/">Welcome, <span data-user-name></span></a></li>
			<li><a href="<c:url value="/logout" />">Logout</a></li>
		</ul>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#/">Employees </a></li>
					<li><a href="#/newemployee">Add New Employee</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" data-ng-view></div>
			<!-- <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div data-ng-controller="FilesController">
				<ul>
					<li data-ng-repeat="file in files">
						{{file}}
					</li>
				</ul>
				<div class="form-inline">
					<input type="file" file-model="myFile"> <button class="btn btn-primary" ng-click="uploadFile()">Upload</button> 
				</div>
			</div>
			</div> -->
			
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="components/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="components/bootstrap.min.js"></script>
	<script src="components/angular.js"></script>
	<script src="components/angular-touch.min.js"></script>
	<script src="components/angular-animate.min.js"></script>
	<script src="components/ui-bootstrap.min.js"></script>
	<script src="components/angular-route.min.js"></script>

	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="components/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="components/ie10-viewport-bug-workaround.js"></script>
	<script src="app/admin/app.js"></script>
	<script src="app/common/modules/HttpExceptionHandeler.js"></script>
	<script src="app/admin/js/controllers/EmployeeController.js"></script>
	<script src="app/admin/js/controllers/NewEmployeeController.js"></script>
	<script src="app/admin/js/providers/EmployeeProvider.js"></script>
	<script src="app/common/directives/rmsDirectives.js"></script>
	<script src="app/common/directives/fileuploadattribute.js"></script>
	<script src="app/common/services/rmsServices.js"></script>
	<script src="app/common/services/fileUploadService.js"></script>
	<script src="app/common/directives/userNameDirective.js"></script>
	<script src="app/admin/js/controllers/FilesController.js"></script>

	
</body>
</html>