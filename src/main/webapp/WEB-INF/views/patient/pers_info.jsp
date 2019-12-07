<!doctype html>
<html lang="en">
  <head>
	<title>Patient Personal Info Page</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
	<meta name="author" content="Artique">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Latest Bootstrap and datatables Bootstrap API ================================================-->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.min.css"/>
	<!-- Date picker css -->
	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
	<!-- My CSS -->
	<link rel="stylesheet" type="text/css" href="../css/myBackgrounds.css">
	
	
	<!-- JQuery, Bootstrap 4 -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<!-- Date picker -->
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <!-- My JS -->
	<script type="text/javascript" src="../js/patient/pers_inf/config.js"></script>
	
	<%-- Injecting error message if we have one --%>
	<c:if test = "${sessionScope.message != null}">
		<%-- Snackbar css and js function definition --%>
		<link rel="stylesheet" type="text/css" href="../css/snackbar.css">
		<script type="text/javascript" src="../js/snackbar.js"></script>
		<%-- Displaying snackbar with error --%>
		<script>$(document).ready(function () {showPopupSnackbar('${sessionScope.message}');});</script>
	</c:if>
  </head>
  
  <body class = "bg-light-green-mari" style="font-family: Ubuntu">
	
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="home">Home Page</a>
				<a class="nav-item nav-link" href="account">Account</a>
				<a class="nav-item nav-link active" href="pers_info">Personal Info<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="doctors">Doctors</a>
				<a class="nav-item nav-link" href="appointments">Appointments</a>
			</div>
		</div>
		<form class="form-inline" action="/sign_out" method = "post">
			<button class="btn btn-info btn-lg my-2 my-sm-0" type="submit">Sign out</button>
		</form>
	</nav>

	<div class = "container my-5">
			<div class="text-center my-2">
				<h3 class="bg-secondary text-white">&darr;Personal Information&darr;</h3>
			</div>
	
			<div class = "row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="firstnameInfo"><b>First Name:</b></label>
					<input class="bg-info text-white form-control" id="firstnameInfo" value="${requestScope.firstname}" readonly></input>   
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="midnameInfo"><b>Middle Name:</b></label>
					<input class="bg-info text-white form-control" id="midnameInfo" value="${requestScope.midname}" readonly></input>  
				</div>
			</div>			
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="lastnameInfo"><b>Last Name:</b></label>
					<input class="bg-info text-white form-control" id="lastnameInfo" value="${requestScope.lastname}" readonly></input>  
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="emailInfo"><b>Email:</b></label>
					<input class="bg-info text-white form-control" id="emailInfo" value="${requestScope.email}" readonly></input>
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="phoneInfo"><b>Phone:</b></label>
					<input class="bg-info text-white form-control" id="phoneInfo" value="${requestScope.phone}" readonly></input>
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="dobInfo"><b>Date of birth:</b></label>
					<input class="bg-info text-white form-control" id="dobInfo" value="${requestScope.dob}" readonly></input>
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="addressInfo"><b>Address:</b></label>
					<input class="bg-info text-white form-control" id="addressInfo" value="${requestScope.address}" readonly></input>
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<label class="bg-dark text-white" for="medHistoryInfo"><b>Medical history:</b></label>
					<textarea class="bg-info text-white form-control" id="medHistoryInfo" readonly>${requestScope.medHistory}</textarea>
				</div>
			</div>
			<div class="row justify-content-md-center my-3">
				<div class="col-6">
					<button class = "form-control btn btn-primary" data-toggle="modal" data-target="#editPersonalInfoModal">Edit Personal Information</button>
				</div>
			</div>
	</div>
	
	<!-- ====== Edit Personal Info Modal Form ====================================================-->
	<div class="modal fade" id="editPersonalInfoModal" tabindex="-1" role="dialog" aria-labelledby="editPersonalInfoLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-center" id="editPersonalInfoLabel"><b>Edit Personal Information</b></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action = "/patient/editPersonalInfo" method = "post">
					<div class="modal-body">
						<div class="row col-12">
							<div class="col-6">
								<h5 class="text-center text-danger">Old Personal Info</h5>
								<div class="col-12 my-2">
									<label for="oldFirstnameInfo"><b>First Name:</b></label>
									<input class="form-control" id="oldFirstnameInfo" value="${requestScope.firstname}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldMidnameInfo"><b>Middle Name:</b></label>
									<input class="form-control" id="oldMidnameInfo" value="${requestScope.midname}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldLastnameInfo"><b>Last Name:</b></label>
									<input class="form-control" id="oldLastnameInfo" value="${requestScope.lastname}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldEmailInfo"><b>Email:</b></label>
									<input class="form-control" id="oldEmailInfo" value="${requestScope.email}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldPhoneInfo"><b>Phone:</b></label>
									<input class="form-control" id="oldPhoneInfo" value="${requestScope.phone}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldDOBInfo"><b>Date of birth:</b></label>
									<input class="form-control" id="oldDOBInfo" value="${requestScope.dob}" readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="oldAddressInfo"><b>Address:</b></label>
									<input class="form-control" id="oldAddressInfo" value="${requestScope.address}" readonly></input>   
								</div>
							</div>
							<div class="col-6">
								<h5 class="text-center text-success">New Personal Info</h5>
								<div class="col-12 my-2">
									<label for="newFirstnameInfo"><b>First Name:</b></label>
									<input class="form-control" id="newFirstnameInfo" value="" maxlength=20 required></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newMidnameInfo"><b>Middle Name:</b></label>
									<input class="form-control" id="newMidnameInfo" value="" maxlength=20 required></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newLastnameInfo"><b>Last Name:</b></label>
									<input class="form-control" id="newLastnameInfo" value="" maxlength=20 required></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newEmailInfo"><b>Email:</b></label>
									<input class="form-control" id="newEmailInfo" value="" maxlength=30 required></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newPhoneInfo"><b>Phone:</b></label>
									<input class="form-control" id="newPhoneInfo" value="" maxlength=25 required></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newDOBInfo"><b>Date of birth:</b></label>
									<input class="form-control" id="newDOBInfo" value="" required readonly></input>   
								</div>
								<div class="col-12 my-2">
									<label for="newAddressInfo"><b>Address:</b></label>
									<input class="form-control" id="newAddressInfo" value="" maxlength=40 required></input>   
								</div>
							</div>
						</div>
					</div><!-- end of modal body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Edit</button>
					</div>
				</form>
			</div><!--modal content-->
		</div><!--modal dialog-->
	</div><!--main modal content division-->

	<div id="snackbar"></div>    <!-- this bar will be showing notifications and errors -->
	

  </body>
</html>