<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
  <head>
	<title>Doctors's See Doctor Page</title>
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
	  <!-- Date picker css -->
	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>	
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
	  <!-- Date picker -->
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
	  <!-- Font Awesome Glyphs -->
	<script src="https://kit.fontawesome.com/7685c16a3d.js" crossorigin="anonymous"></script>
	  <!-- My scripts -->
	<script type="text/javascript" src="${context}/static/js/utils/utils.js"></script>
	<script type="text/javascript" src="${context}/static/js/doctor/doctors/dataTableConfig.js"></script>
	<script type="text/javascript" src="${context}/static/js/doctor/doctors/datePickerConfig.js"></script>
	
	<%-- Injecting message only if we have one --%>
	<c:if test = "${sessionScope.message != null}">
		<%-- Snackbar css and js function definition --%>
		<link rel="stylesheet" type="text/css" href="${context}/static/css/snackbar.css">
		<script type="text/javascript" src="${context}/static/js/snackbar.js"></script>
		<%-- Displaying snackbar with error --%>
		<script>$(document).ready(function () {showPopupSnackbar('${sessionScope.message}');});</script>
	</c:if>
  </head>
  
  <body class = "bg-light-green-mari" style="font-family: Ubuntu">
	
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarAltContent" aria-controls="navbarAltContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarAltContent">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="home">Home</a>
				<a class="nav-item nav-link" href="account">Account</a>
				<a class="nav-item nav-link" href="profile">Personal Information</a>
				<a class="nav-item nav-link active" href="doctors">See Doctor<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="patients">Find Patient</a>
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
            <h3 class="bg-secondary text-white">&darr;Over here all doctors available for you&darr;</h3>
        </div>
	
		<div class="table-responsive mb-3">
            <table id="dtDoctors" class="table table-hover table-bordered table-sm bg-info text-white">
                <thead>
                    <tr class="bg-dark text-white">
						<th class="th-sm">ID</th>
                        <th class="th-sm">First Name</th>
                        <th class="th-sm">Middle Name</th>
                        <th class="th-sm">Last Name</th>
                        <th class="th-sm">Specialization</th>
						<th class="th-sm">Degree</th>
						<th class="th-sm">See Doctor</th>
                    </tr>
                </thead>

                <tbody>
					<c:forEach var="doctor" items="${requestScope.doctors}">
						<tr>
							<td>${doctor.id}</td>
							<td>${doctor.staffEntity.patient.firstname}</td>
							<td>${doctor.staffEntity.patient.middlename}</td>
							<td>${doctor.staffEntity.patient.lastname}</td>
							<td>${doctor.specialization}</td>
							<td>${doctor.degree}</td>
							<td>
								<div class="row justify-content-center">
									<button class="btn" data-toggle="modal" data-target="#datePickerModal">
										<i class=" fas fa-address-book"></i>
									</button>
								</div>
							</td>	
						</tr>
					</c:forEach>
                </tbody>

                <tfoot>
                    <tr class="bg-dark text-white">
						<th class="th-sm">ID</th>
                        <th class="th-sm">First Name</th>
                        <th class="th-sm">Middle Name</th>
                        <th class="th-sm">Last Name</th>
                        <th class="th-sm">Specialization</th>
						<th class="th-sm">Degree</th>
						<th class="th-sm">See Doctor</th>
                    </tr>
                </tfoot>
            </table>
        </div> 
	</div>
	
	<!-- ====== Date picker modal ====================================================-->
	<div class="modal fade" id="datePickerModal" tabindex="-1" role="dialog" aria-labelledby="datePickerModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-center" id="datePickerModalLabel"><b>Choose date please</b></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action = "MakeAnAppointment" method = "post">
					<div class="modal-body">
						<div class="container my-5">
							<input type="hidden" id="selectedDoctorID" name="doctorID" value="">
							<div class="row justify-content-center my-3">
								<input class="form-control" id="appointmentDatePicker" name="date" value="2020-12-12" required readonly></input> 
							</div>
							<div class="row justify-content-center my-3">
								<label for="formCommentSection"> You can leave the comment if you want: </label>
								<textarea class="form-control" id="formCommentSection" name="comment" maxlength="50"></textarea> 
							</div>
						</div>
					</div><!-- end of modal body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Make An Appointment</button>
					</div>
				</form>
			</div><!--modal content-->
		</div><!--modal dialog-->
	</div><!--main modal content division-->

	<%-- Injecting message only if we have one --%>
	<c:if test = "${sessionScope.message != null}">
		<div id="snackbar"></div>
	</c:if>

  </body>
</html>