package com;

import java.sql.*;

public class Complaint {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paflab", "root", "");
			System.out.println("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Error connected");
		}
		return con;
	}

	public String insertComplaint(String name, String email, String pnumber, String category, String complaint, String area, String remarks) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into complaints(`complaintID`, `complainerName` , `email`,`phoneNumber`,`complaintCategory`,`complaint`, `issueArea` ,`remarks`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);			
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setInt(4, Integer.parseInt(pnumber));			
			preparedStmt.setString(5, category);
			preparedStmt.setString(6, complaint);
			preparedStmt.setString(7, area);
			preparedStmt.setString(8, remarks);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newComplaints = readComplaints();
			output = "{\"status\":\"success\", \"data\": \"" +
			newComplaints + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":"
					+ "\"Error while inserting the complaint.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String readComplaints()
	{
		String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
	{
			return "Error while connecting to the database for reading.";
	}
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Complainer Name</th>"
				+ "<th>Email</th>" 
				+ "<th>Phone Number</th>" 
				+ "<th>Complaint category</th>"
				+ "<th>Complaint</th>" 
				+ "<th>Issue Area</th>" 
				+ "<th>Remarks</th>"
				+ "<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from complaints";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{
			String complaintID = Integer.toString(rs.getInt("complaintID"));
			String complainerName = rs.getString("complainerName");
			String email = rs.getString("email");
			String phoneNumber = Integer.toString(rs.getInt("phoneNumber"));
			String complaintCategory = rs.getString("complaintCategory");
			String complaint = rs.getString("complaint");
			String issueArea = rs.getString("issueArea");
			String remarks = rs.getString("remarks");
			
		// Add into the html table
			output += "<tr><td><input id='hidComplaintIDUpdate' name='hidComplaintIDUpdate' type='hidden' value='" + complaintID + "'>"
					+ complainerName + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + phoneNumber + "</td>";
			output += "<td>" + complaintCategory + "</td>";
			output += "<td>" + complaint + "</td>";
			output += "<td>" + issueArea + "</td>";
			output += "<td>" + remarks + "</td>";
			
			
		// buttons							
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='" + complaintID + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + complaintID + "'></td></tr>";
				
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
	
	catch (Exception e)
	{
		output = "Error while reading the complaints.";
		System.err.println(e.getMessage());
		
	}
		return output;
}

	public String updateComplaint(String ID, String name, String email, String pnumber, String category, String complaint, String area, String remarks) 
	{
		String output = "";
		
		try {
				Connection con = connect();
	
				if (con == null) 
				{
					return "Error while connecting to the database for updating.";
				}
	
				// create a prepared statement
				String query = "UPDATE complaints SET complainerName=?,email=?,phoneNumber=?,complaintCategory=?,complaint=?,issueArea=?,remarks=?" + "WHERE complaintID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
	
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, email);
				preparedStmt.setInt(3, Integer.parseInt(pnumber));
				preparedStmt.setString(4, category);
				preparedStmt.setString(5, complaint);
				preparedStmt.setString(6, area);
				preparedStmt.setString(7, remarks);
				preparedStmt.setInt(8, Integer.parseInt(ID));
	
				// execute the statement
				preparedStmt.execute();
				con.close();
	
				String newComplaints = readComplaints();
				output = "{\"status\":\"success\", \"data\": \"" +
				newComplaints + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the complaint.\"}";
				System.err.println(e.getMessage());
			}
		
			return output;
		}

	public String deleteComplaint(String complaintID) 
	{
		String output = "";

		try 
		{
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from complaints where complaintID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(complaintID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newComplaints = readComplaints();
			output = "{\"status\":\"success\", \"data\": \"" +
			newComplaints + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the complaint.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
