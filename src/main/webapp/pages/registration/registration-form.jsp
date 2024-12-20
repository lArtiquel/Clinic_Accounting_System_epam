<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
  <head>
	<title>Registration</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
	<meta name="author" content="Artique">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Latest Bootstrap and datatables Bootstrap API ================================================-->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
	<!-- Date picker css -->
	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
	<!-- My Page Favicon -->
	<link rel="icon" type="image/png" href="${context}/static/images/icons/favicon.ico">
	<!-- My CSS -->
	<link rel="stylesheet" type="text/css" href="${context}/static/css/myBackgrounds.css">
	
	
	<!-- JQuery, Bootstrap 4 -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<!-- Date picker -->
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
	
    <!-- My JS -->
	<script type="text/javascript" src="${context}/static/js/utils/utils.js"></script>
	<script type="text/javascript" src="${context}/static/js/registration/datePickerConfig.js"></script>

	<%-- Injecting message if we only have one --%>
	<c:if test = "${sessionScope.message != null}">
	  <%-- Snackbar css and js function definition --%>
	  <link rel="stylesheet" type="text/css" href="${context}/static/css/snackbar.css">
	  <script type="text/javascript" src="${context}/static/js/snackbar.js"></script>
	  <%-- Displaying snackbar with error --%>
	  <script>$(document).ready(function () {showPopupSnackbar('${sessionScope.message}');});</script>
	</c:if>
  </head>
  
  <body class = "bg-light-green-mari" style="font-family: Ubuntu">

	<div class = "container">
	
		<div class="text-center mt-5 mb-2">
			<h3 class="bg-secondary text-white">&darr;Please, fill up an application form&darr;</h3>
		</div>

		<form action="registration/ProcessRegistrationForm" method="post">
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="usernameInput"><b>Username:</b></label>
					<input type="text" class="form-control" id="usernameInput" name="username" required minlength="8" />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="passwordInput"><b>Password:</b></label>
					<input type="password" class="form-control" id="passwordInput" name="password" required minlength="8" />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="firstname"><b>First Name:</b></label>
					<input class="form-control" id="firstname" maxlength = 20 name="firstname" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="midname"><b>Middle Name:</b></label>
					<input class="form-control" id="midname"  maxlength = 20 name="midname" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="lastname"><b>Last Name:</b></label>
					<input class="form-control" id="lastname"  maxlength = 20 name="lastname" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="dobDatePicker"><b>Date of birth:</b></label>
					<input class="form-control" id="dobDatePicker" name="dob" value="1990-12-12" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="phone"><b>Phone:</b></label>
					<input class="form-control" id="phone" maxlength=25 name="phone" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="email"><b>Email:</b></label>
					<input class="form-control" id="email" maxlength=30 name="email" required />
				</div>
			</div>
			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<label class="bg-dark text-white" for="homeAddress"><b>Home address:</b></label>
					<input class="form-control" id="homeAddress" maxlength=40 name="homeAddress" required />
				</div>
			</div>

			<div class = "row justify-content-center my-3">
				<div class="col-xs-12 col-lg-6">
					<button type="submit" class="btn btn-primary form-control">Submit Application</button>
				</div>
			</div>
		</form>

	</div>

	<c:if test = "${sessionScope.message != null}">
		<div id="snackbar"></div>
	</c:if>

  </body>
</html>