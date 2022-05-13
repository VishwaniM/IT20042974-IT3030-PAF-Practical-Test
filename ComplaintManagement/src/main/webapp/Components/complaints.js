$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// Save ==================================
$(document).on("click", "#btnSave", function(event) 
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateComplaintForm();
	if (status != true) 
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidComplaintIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
			url: "ComplaintsAPI",
			type: type,
			data: $("#formComplaint").serialize(),
			dataType: "text",
			complete: function(response, status) 
			{
				onComplaintSaveComplete(response.responseText, status);
			}
		});
});

function onComplaintSaveComplete(response, status) {
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divComplaintsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error") 
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") 
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else 
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidComplaintIDSave").val("");
	$("#formComplaint")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidComplaintIDSave").val($(this).data("itemid"));
	$("#complainerName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#email").val($(this).closest("tr").find('td:eq(1)').text());
	$("#phoneNumber").val($(this).closest("tr").find('td:eq(2)').text());
	$("#complaintCategory").val($(this).closest("tr").find('td:eq(3)').text());
	$("#complaint").val($(this).closest("tr").find('td:eq(4)').text());
	$("#issueArea").val($(this).closest("tr").find('td:eq(5)').text());
	$("#remarks").val($(this).closest("tr").find('td:eq(6)').text());
	
	
});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "ComplaintsAPI",
			type: "DELETE",
			data: "complaintID=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onComplaintDeleteComplete(response.responseText, status);
			}
		});
});

function onComplaintDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divComplaintsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================
function validateComplaintForm() {

	// Complainer Name
	if ($("#complainerName").val().trim() == "") {
		return "Insert Complainer Name.";
	}

	// Email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}

	// Phone Number-------------------------------
	if ($("#phoneNumber").val().trim() == "") {
		return "Insert Phone Numbe.";
	}

	// is numerical value
	var tmpPhoneNumber = $("#phoneNumber").val().trim();
	if (!$.isNumeric(tmpPhoneNumber)) {
		return "Insert a numerical value for Phone Number.";
	}

	//  Category------------------------
	if ($("#complaintCategory").val().trim() == "") {
		return "Insert Complaint Category.";
	}

	//  Complaint------------------------
	if ($("#complaint").val().trim() == "") {
		return "Insert Complaint.";
	}

	//  Issue Area------------------------
	if ($("#issueArea").val().trim() == "") {
		return "Insert Issue Area.";
	}

	//  Remarks------------------------
	if ($("#remarks").val().trim() == "") {
		return "Insert Remarks.";
	}
	return true;
}

