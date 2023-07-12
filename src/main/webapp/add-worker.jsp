<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	Admin admin_session = (Admin) request.getSession().getAttribute("admin_session");    
	if(admin_session != null) { %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Worker</title>
<link rel="stylesheet" type="text/css" href="css/input.css">
</head>
<body>

	<h1>Add new worker</h1>
	<hr>
	<form action="addNewWorker" method="post">
		
		Worker full name: <br>
		<input type="text" name="workerName" > 
		<button type="submit" name="action" value="addNewWorker">ADD WORKER</button> 
	</form>
	<br>
	<span style="color: green; font-size: 20px;">${requestScope.successMsg}</span>
	<span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>
	
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>
