<%@page import="com.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaints Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/complaints.js"></script>
<script>
function resetForm() {
    document.getElementById("formComplaint").reset();
}
</script>
</head>
<body>

	<div class="container-fluid">
			<nav class="navbar navbar-expand-lg navbar-light bg-light" >
				  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
				    <span class="navbar-toggler-icon"></span>
				  </button>
				  <a class="navbar-brand" href="#">Complaint Management</a>
				
				  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
				    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				      <li class="nav-item active">
				        <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
				      </li>				     
				    </ul>
				  </div>
				</nav>
			<div class="row col-9 mx-2 my-5">
				<div class="col-2 shadow-lg p-3 mb-5 bg-white rounded col-md-5 offset-md-5" >
				
					<h2 class="text-center mt-3 mb-3">Complaint Management </h2>
					
					<form id="formComplaint" name="formComplaint" class="px-4">
					
						Complainer Name: 
						<input id="complainerName" name="complainerName" type="text" class="form-control form-control-sm mb-2"> 
						
						Email:
						<input id="email" name="email" type="text" class="form-control form-control-sm mb-2"> 
						
						Phone Number: 
						<input id="phoneNumber" name="phoneNumber" type="text" class="form-control form-control-sm mb-2"> 
						
						Category:
						<select id="complaintCategory" name="complaintCategory" type="text" class="form-control form-control-sm mb-2">						
							<option selected value="Breakdown">Breakdown</option>
							<option value="Service Request">Service Request</option>
						</select>
						      
						Complaint: 					 
						<select id="complaint" name="complaint" type="text" class="form-control form-control-sm mb-2">
							<option selected value="" >Please select</option>
							<option value="Supply failed at premises">Supply failed at premises</option>
							<option value="Supply failed in area">Supply failed in area</option>
							<option value="Broken service wire">Broken service wire</option>
							<option value="Broken conductor">Broken conductor</option>
							<option value="Voltage flickering">Voltage flickering</option>
							<option value="High voltage">High voltage</option>
							<option value="Meter burning">Meter burning</option>
							<option value="Neutral leak">Neutral leak</option>
							<option value="Road accident cause Cable / Conductor damage">Road accident cause Cable / Conductor damage</option>
							<option value="Flood">Flood</option>
							<option value="Other">Other</option>
						
						</select>
						
						Issue Area: 
						<input id="issueArea" name="issueArea" type="text" class="form-control form-control-sm mb-2"> 
						
						Remarks: 						
						<textarea id="remarks" name="remarks" type="text" class="form-control form-control-sm mb-2" rows="3"></textarea>
						 
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-sm btn-primary btn-block mt-3"> 
						<input type="hidden" id="hidComplaintIDSave" name="hidComplaintIDSave" value="">
						<input type="button" value="Clear" class="btn btn-secondary btn-sm btn-block my-3" onclick="resetForm();">
					</form>
					
					<div id="alertSuccess" class="alert alert-success mt-3 mx-4"></div>
					<div id="alertError" class="alert alert-danger mt-3 mx-4"></div>
					
					
				</div>
				<div id="divComplaintsGrid" class="shadow-lg p-3 mb-5 bg-white rounded" >
						<%
						Complaint complaintObj = new Complaint();
						out.print(complaintObj.readComplaints());
						%>
				</div>
			</div>
	</div>
</body>
</html>