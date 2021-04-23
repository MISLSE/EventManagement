package EventMNG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Event {
	
	
	//********** connecting method start **********
	
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eventmanagment", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	//********* connecting method end **********
	
	//******** data insert method start ********
	
	public String insertEvent(String eventID, String eventName, String category,String description ,String startingTime,String closingTime)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database"; 
	 } 
	 // create a prepared statement
	 String query = "insert into event(`eventID`,`eventName`,`category`,`description`,`startingTime`,`closingTime`)"+ " values (?, ?, ?, ?, ?,?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 
	 preparedStmt.setString(1, eventID); 
	 preparedStmt.setString(2, eventName); 
	 preparedStmt.setString(3, category);
	 preparedStmt.setString(4, description);
	 preparedStmt.setString(5, startingTime);
	 preparedStmt.setString(6, closingTime); 
	//execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while inserting"; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

	//********** data insert method end *********

	
	//*********  data delete method start  *******
	
	public String deleteItem(String eventID)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from event where eventID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(eventID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	} 

	//*********  data delete method end  *********
	
	
	//******** data update method start ********

		public String updateEvent(String eventID, String eventName, String category,String description ,String startingTime,String closingTime)
		{
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database"; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE event SET eventName=? , category=?, description=?, startingTime=?, closingTime=?  WHERE eventID =? "; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 
		 
		 preparedStmt.setString(1, eventName); 
		 preparedStmt.setString(2, category);
		 preparedStmt.setString(3, description);
		 preparedStmt.setString(4, startingTime);
		 preparedStmt.setString(5, closingTime);
		 preparedStmt.setInt(6, Integer.parseInt(eventID)); 
		 
		//execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "updated successfully"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while updating.."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

		//********** data update method end *********
	
	
	
	
	
	
	
	
	

	//********** Data view Method Start *********
	
	public String readevent()
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
	 
	 output = "<table border='1'><tr><th>Event ID</th>" 
	 +"<th>Event Name</th><th>Category</th>"
	 + "<th>Description</th>" 
	 + "<th>Starting Time</th><th>Closing Time</th></tr>"; 
	 String query = "select * from event"; 
	 Statement stmt =  con.createStatement(); 
	 ResultSet rs =  stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	
	 while (rs.next()) 
	 { 
	 String eventID = Integer.toString(rs.getInt("eventID"));  
	 String eventName = rs.getString("eventName"); 
	 String category = rs.getString("category");
	 String description = rs.getString("description"); 
	 String startingTime = rs.getString("startingTime"); 
	 String closingTime = rs.getString("closingTime"); 
	 
	 // Add a row into the html table
	 
	 output += "<tr><td>" + eventID + "</td>"; 
	 output += "<td>" + eventName + "</td>"; 
	 output += "<td>" + category + "</td>"; 
	 output += "<td>" + description + "</td>"; 
	output += "<td>" + startingTime + "</td>";
	output += "<td>" + closingTime + "</td>"; 
	
	
	// buttons
	output += "<td><form method='post' action='EventMNG.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'>"
	 + "<input name='eventID' type='hidden' value='" + eventID + "'>" 
	 + "</form></td></tr>"; 
	
	
	
	
	
	
	
	 
	
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
	
	
	//********** Data view Method End  *********
	public static void main(String[] args) {
		
		Event a = new Event();
		a.connect();
		
		
		
	}

}
  
