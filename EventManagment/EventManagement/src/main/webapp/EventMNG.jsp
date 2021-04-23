<%@ page import="EventMNG.Event"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<% 
 //Insert item---------------------------------- 
if (request.getParameter("eventID") != null) 
 { 
 Event itemObj = new Event(); 
 String stsMsg = itemObj.insertEvent(request.getParameter("eventID"), 
 request.getParameter("eventName"), 
 request.getParameter("category"), 
 request.getParameter("description"),
 request.getParameter("startingTime"),
 request.getParameter("closingTime"));
 session.setAttribute("statusMsg", stsMsg); 
 } 

//update item---------------------------------- 
if (request.getParameter("btnUpdate") != null) 
{ 
Event itemObj = new Event(); 
String stsMsg = itemObj.updateEvent(request.getParameter("eventID"), 
request.getParameter("eventName"), 
request.getParameter("category"),
request.getParameter("description"),
request.getParameter("startingTime"),
request.getParameter("closingTime")); 
session.setAttribute("statusMsg", stsMsg); 
} 



//Delete item----------------------------------
if (request.getParameter("btnRemove") != null) 
{ 
Event itemObj = new Event(); 
String stsMsg = itemObj.deleteItem(request.getParameter("eventID")); 
session.setAttribute("statusMsg", stsMsg); 
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Management</title>
</head>
<body>
<h1>Event Management</h1>
<form method="post" action="EventMNG.jsp">

 Event ID: <input name="eventID" type="text"><br> 
 Event name: <input name="eventName" type="text"><br> 
 Category: <input name="category" type="text"><br> 
 description: <input name="description" type="text"><br>
 StartingTime<input name="startingTime" type="text"><br> 
 ClosingTime<input name="closingTime" type="text"><br>
 
 <input name="btnSubmit" type="submit" value="Save">
 <input name="btnUpdate" type="submit" value="update">

</form>
<%
 out.print(session.getAttribute("statusMsg")); 
%>
<br>
<%
 Event eventObj = new Event(); 
 out.print(eventObj.readevent()); 
%>
</body>
</html>
