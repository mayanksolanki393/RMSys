<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html lang="en" data-ng-app="myRequirmentApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Requirement Management</title>

<!-- Bootstrap core CSS -->
<link href="components/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="components/dashboard.css" rel="stylesheet">
<link href="app/emp-manager/css/MainStyle.css" rel="stylesheet">
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#/">Resource Management System <sup
				class="label label-danger">Admin</sup></a>
		</div>
		<ul class="nav navbar-nav navbar-right" style="margin-right:4px;">
			<li><a href="#/">Welcome, [Admin name]</a></li>
			<li><a href="#/">Logout</a></li>
		</ul>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#/listProject">Projects</a></li>
					<li><a href="#/addProject">Add New Project</a></li>
					<li><a href="#/listUser">Users</a></li>
					<li><a href="#/addUser">Add New User</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				data-ng-view></div>
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
	<script src="common/modules/HttpExceptionHandeler.js"></script>
	<script src="app/emp-manager/app.js"></script>
	<script src="app/emp-manager/js/controllers/project/AddProjectController.js"></script>
	<script src="app/emp-manager/js/controllers/project/ProjectListController.js"></script>
	<script src="app/emp-manager/js/controllers/project/UpdateProjectController.js"></script>
	<script src="app/emp-manager/js/controllers/requirement/RequirementListController.js"></script>
	<script src="app/emp-manager/js/controllers/requirement/AddRequirementController.js"></script>
	<script src="app/emp-manager/js/controllers/requirement/RequirementListController.js"></script>
	<script src="app/emp-manager/js/controllers/user/UserListController.js"></script>
	<script src="app/emp-manager/js/controllers/user/AddUserController.js"></script>
	<script src="app/emp-manager/js/controllers/user/UpdateUserController.js"></script>
	<script src="app/emp-manager/js/providers/ProjectProvider.js"></script>
	<script src="app/emp-manager/js/providers/RequirementProvider.js"></script>
	<script src="app/emp-manager/js/providers/UserProvider.js"></script>
	<script src="app/emp-manager/js/providers/ElaborationProvider.js"></script>
	
</body>
</html>