<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%-- Saying JSP set values in page header settings that browser not allowed to store page in cache --%>
<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");	// for HTTP v1.1
	response.setHeader("Pragma", "no-cache");									// for HTTP v1.0
	response.setHeader("Expires", "0");											// for proxy connections
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
	<title>Clinic Accounting System</title>
    <meta charset="utf-8">
	<meta name="author" content="Artique">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- My Page Favicon -->
	<link rel="icon" type="image/png" href="${context}/static/images/icons/favicon.ico">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
	<!-- Ubuntu font -->
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu">
	<!-- My CSS -->
	<link rel="stylesheet" type="text/css" href="${context}/static/css/myBackgrounds.css">

	<!-- Jquery and Twitter Bootstrap js -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
	

	<%-- Injecting error message if we have one --%>
	<c:if test = "${sessionScope.message != null}">
		<%-- Snackbar css and js function definition --%>
		<link rel="stylesheet" type="text/css" href="${context}/static/css/snackbar.css">
		<script type="text/javascript" src="${context}/static/js/snackbar.js"></script>
		<%-- Displaying snackbar with error --%>
		<script>$(document).ready(function () {showPopupSnackbar('${sessionScope.message}');});</script>
	</c:if>

  </head>
  <body class = "bg-light-green-mari" style="font-family: Ubuntu">
	
	<h2 class = "my-5 text-center" style="color:#800080">Welcome to "Clinic Accounting System" Web App!</h2>

	</br>

	<div class="my-4 d-flex align-items-center flex-column justify-content-center h-100 text-white" id="header">

		<h2 class="display-4">Login</h2>

		<form action="ProcessLoginForm" method = "post">
			<div class="form-group my-2">
				<input class="form-control form-control-lg" name="username" placeholder="Username" type="text" required>
			</div>
			<div class="form-group my-2">
				<input class="form-control form-control-lg" name="password" placeholder="Password" type="password" required>
			</div>
			<div class="form-row my-2">
				<div class="col-md-6">
				<input id="rem_me_Cb" type="checkbox" name="remember-me">
				<label for="rem_me_Cb">Remember me</label>
				</div>
				<div class="col-md-6 text-right">
				<a href="recovery/enter-username"><i>Forgot</i></a>
				</div>
			</div>
			<div class="form-group my-2">
				<button type="submit" class="btn btn-info btn-lg btn-block text-white">Sign In</button>
			</div>
		</form>
			
		<div class = "row justify-content-center">
			Not registered yet? <a href="registration"><i>Register now!</i></a>
		</div>

	</div>

	<c:if test = "${sessionScope.message != null}">
		<div id="snackbar"></div>
	</c:if>

  </body>	
</html>