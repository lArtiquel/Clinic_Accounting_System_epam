<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
  <head>
	<title>Patient's Home Page</title>
	  <!-- Required meta tags -->
    <meta charset="utf-8">
	<meta name="author" content="Artique">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <!-- My Page Favicon -->
	<link rel="icon" type="image/png" href="${context}/static/images/icons/favicon.ico">
	  <!-- Latest Bootstrap and datatables Bootstrap API ================================================-->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
	  <!-- Latest Datatable's Bootstrap API ==============================================================-->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"/>
	  <!-- Datatable's Buttons, Responsivness and Selection ==============================================-->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.bootstrap4.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
	  <!-- My CSS -->
	<link rel="stylesheet" type="text/css" href="${context}/static/css/myBackgrounds.css">
	  <!--JQuery, Bootstrap 4, Buttons, Responsivness, selection here-->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.bootstrap4.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
	  <!-- Font Awesome Glyphs -->
	<script src="https://kit.fontawesome.com/7685c16a3d.js" crossorigin="anonymous"></script>
	  <!-- My scripts -->
	<script type="text/javascript" src="${context}/static/js/patient/home/dataTableConfig.js"></script>
  </head>
  
  <body class = "bg-light-green-mari" style="font-family: Ubuntu">
	
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarAltContent" aria-controls="navbarAltContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarAltContent">
			<div class="navbar-nav">
				<a class="nav-item nav-link active" href="home">Home<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="account">Account</a>
				<a class="nav-item nav-link" href="profile">Personal Information</a>
				<a class="nav-item nav-link" href="doctors">See Doctor</a>
				<a class="nav-item nav-link" href="appointments">My Appointments</a>
			</div>
		</div>
		<form class="form-inline" action="../logout" method = "post">
			<button class="btn my-1" type="submit">
				<i class="fas fa-sign-out-alt"></i>
			</button>
		</form>
	</nav>
	
	<!-- content here -->
	<div class = "container">

	    <div class="text-center my-3">
            <h2 class="text-primary"><b>Welcome, ${requestScope.user}!!!</b></h2>
        </div>

        <div class="text-center my-2">
            <h3 class="bg-secondary text-white">&darr;Over here events available for you&darr;</h3>
        </div>
	
		<div class="table-responsive mb-3">
            <table id="dtEvents" class="table table-hover table-bordered table-sm bg-info text-white">
                <thead>
                    <tr class="bg-dark text-white">
                        <th class="th-sm">Event</th>
                        <th class="th-sm">Description</th>
                        <th class="th-sm">Start date</th>
                        <th class="th-sm">End Date</th>
                    </tr>
                </thead>

                <tbody>
					<c:forEach var="event" items="${requestScope.events}">
						<tr>
							<td>${event.header}</td>
							<td>${event.content}</td>
							<td>${event.startDate.toString()}</td>
							<td>${event.endDate.toString()}</td>
						</tr>
					</c:forEach>
                </tbody>

                <tfoot>
                    <tr class="bg-dark text-white">
                        <th class="th-sm">Event</th>
                        <th class="th-sm">Description</th>
                        <th class="th-sm">Start date</th>
                        <th class="th-sm">End Date</th>
                    </tr>
                </tfoot>
            </table>
        </div> 
	</div>

  </body>
</html>