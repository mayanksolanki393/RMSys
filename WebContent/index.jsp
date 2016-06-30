<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<!-- Bootstrap core CSS -->
<link href="components/bootstrap.min.css" rel="stylesheet">
<link href="app/common/css/MainStyle.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Requirement Management System</a>
		</div>
	</nav>
	
	<div class="container well login-box center">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">username or password was invalid</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div class="alert alert-info">logged out successfully.</div>
		</c:if>
		<form role="form" method="post" action="<c:url value='/j_spring_security_check'/>" accept-charset=utf-8>
			<div class="form-group">
				<label for="email">Username</label> <input type="text"
					class="form-control" name="username" placeholder="username"
					autofocus="autofocus">
			</div>
			<div class="form-group">
				<label for="pwd">Password</label> <input type="password"
					class="form-control" name="password" placeholder="password"
					autocomplete="off">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
		</form>
	</div>
</body>
</html>