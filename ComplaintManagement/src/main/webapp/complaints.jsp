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
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Complaint Management </h1>
				
				<form id="formComplaint" name="formComplaint">
				
					Complainer Name: 
					<input id="complainerName" name="complainerName" type="text" class="form-control form-control-sm"> <br> 
					
					Email:
					<input id="email" name="email" type="text" class="form-control form-control-sm"> <br> 
					
					Phone Number: 
					<input id="phoneNumber" name="phoneNumber" type="text" class="form-control form-control-sm"> <br> 
					
					Category: 
					<input id="complaintCategory" name="complaintCategory" type="text" class="form-control form-control-sm"> <br>
					
					Complaint: 
					<input id="complaint" name="complaint" type="text" class="form-control form-control-sm"> <br> 
					
					Issue Area: 
					<input id="issueArea" name="issueArea" type="text" class="form-control form-control-sm"> <br> 
					
					Remarks: 
					<input id="remarks" name="remarks" type="text" class="form-control form-control-sm"> <br>					
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidComplaintIDSave" name="hidComplaintIDSave" value="">
					
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divComplaintsGrid">
					<%
					Complaint complaintObj = new Complaint();
					out.print(complaintObj.readComplaints());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>