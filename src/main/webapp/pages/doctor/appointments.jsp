<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
  <head>
	<title>Doctors's Appointments</title>
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
	<script type="text/javascript" src="${context}/static/js/doctor/appointments/dataTablesConfig.js"></script>
	
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
				<a class="nav-item nav-link" href="doctors">See Doctor</a>
				<a class="nav-item nav-link" href="patients">Find Patient</a>
				<a class="nav-item nav-link active" href="appointments">My Appointments<span class="sr-only">(current)</span></a>
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

		<div class = "my-4">
			<div class="text-center mb-2">
				<h3 class="bg-secondary text-white">&darr;Your appointments as a patient here&darr;</h3>
			</div>
		
			<div class="table-responsive">
				<table id="dtAsPatientAppointments" class="table table-hover table-bordered table-sm bg-info text-white">
					<thead>
						<tr class="bg-dark text-white">
							<th class="th-sm">Doc ID</th>
							<th class="th-sm">Doc's Specialization</th>
							<th class="th-sm">Doc's Name</th>
							<th class="th-sm">Doc's Midname</th>
							<th class="th-sm">Doc's Lastname</th>
							<th class="th-sm">App's Date</th>
							<th class="th-sm">Number in queue</th>
							<th class="th-sm">Comment</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="appointment" items="${requestScope.asPatientAppointments}">
							<tr>
								<td>${appointment.doctor.id}</td>
								<td>${appointment.doctor.specialization}</td>
								<td>${appointment.doctor.staffEntity.patient.firstname}</td>
								<td>${appointment.doctor.staffEntity.patient.middlename}</td>
								<td>${appointment.doctor.staffEntity.patient.lastname}</td>
								<td>${appointment.date.toString()}</td>
								<td>${appointment.numberInQueue}</td>
								<td>${appointment.comment}</td>
							</tr>
						</c:forEach>
					</tbody>

					<tfoot>
						<tr class="bg-dark text-white">
							<th class="th-sm">Doc ID</th>
							<th class="th-sm">Doc's Specialization</th>
							<th class="th-sm">Doc's Name</th>
							<th class="th-sm">Doc's Midname</th>
							<th class="th-sm">Doc's Lastname</th>
							<th class="th-sm">App's Date</th>
							<th class="th-sm">Number in queue</th>
							<th class="th-sm">Comment</th>
						</tr>
					</tfoot>
				</table>
			</div> 
		</div>
		
		<div class = "mt-2 mb-5">
			<div class="text-center mt-5 mb-2">
				<h3 class="bg-secondary text-white">&darr;Your appointments as a doctor here&darr;</h3>
			</div>
		
			<div class="table-responsive">
				<table id="dtAsDoctorAppointments" class="table table-hover table-bordered table-sm bg-info text-white">
					<thead>
						<tr class="bg-dark text-white">
							<th class="th-sm">App's Date</th>
							<th class="th-sm">Number in queue</th>
							<th class="th-sm">Comment</th>
							<th class="th-sm">Patient ID</th>
							<th class="th-sm">Patient's Name</th>
							<th class="th-sm">Patient's Midname</th>
							<th class="th-sm">Patient's Lastname</th>
							<th class="th-sm">Patient's DOB</th>
							<th class="th-sm">Patient's Email</th>
							<th class="th-sm">Patient's Phone</th>
							<th class="th-sm">Patient's Address</th>
							<th class="th-sm">Patient's Med. History</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="appointment" items="${requestScope.asDoctorAppointments}">
							<tr>
								<td>${appointment.date}</td>
								<td>${appointment.numberInQueue}</td>
								<td>${appointment.comment}</td>
								<td>${appointment.patient.id}</td>
								<td>${appointment.patient.firstname}</td>
								<td>${appointment.patient.middlename}</td>
								<td>${appointment.patient.lastname}</td>
								<td>${appointment.patient.dob.toString()}</td>
								<td>${appointment.patient.email}</td>
								<td>${appointment.patient.phone}</td>
								<td>${appointment.patient.address}</td>
								<td>${appointment.patient.medicalHistory}</td>
							</tr>
						</c:forEach>
					</tbody>

					<tfoot>
						<tr class="bg-dark text-white">
							<th class="th-sm">App's Date</th>
							<th class="th-sm">Number in queue</th>
							<th class="th-sm">Comment</th>
							<th class="th-sm">Patient ID</th>
							<th class="th-sm">Patient's Name</th>
							<th class="th-sm">Patient's Midname</th>
							<th class="th-sm">Patient's Lastname</th>
							<th class="th-sm">Patient's DOB</th>
							<th class="th-sm">Patient's Email</th>
							<th class="th-sm">Patient's Phone</th>
							<th class="th-sm">Patient's Address</th>
							<th class="th-sm">Patient's Med. History</th>
						</tr>
					</tfoot>
				</table>
			</div> 
		</div>
		
	</div>
	
	<!-- ====== End appointment form modal ======-->
	<div class="modal fade" id="closeAppointmentModal" tabindex="-1" role="dialog" aria-labelledby="closeAppointmentModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center" id="closeAppointmentModalLabel"><b>Here you can leave a note to patient's medical history</b></h3>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action = "CloseAppointment" method = "post">
					<input type="hidden" name="patientID" id="closeAppointmentForm_PatientID">
					<input type="hidden" name="date" id="closeAppointmentForm_Date">
					<input type="hidden" name="numberInQueue" id="closeAppointmentForm_NumberInQueue">
					<div class="modal-body">
						<div class="form-row my-5">
							<textarea class="form-control" name="note" id="noteToMedicalHistory" rows=7></textarea>
						</div>
					</div><!-- end of modal body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Dismiss</button>
						<button type="submit" class="btn btn-primary">Close appointment</button>
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



