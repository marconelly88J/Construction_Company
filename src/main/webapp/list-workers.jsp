<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	Admin admin_session = (Admin) request.getSession().getAttribute("admin_session");
	ArrayList<User> list_all_workers = (ArrayList<User>) request.getAttribute("list_all_workers");
	if(admin_session != null) { %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Worker List</title>
<link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<body>

	<h1>Worker List Database</h1>
	<hr>
	
	
	<table border="1" style="width: 40%;">
	
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>action</th>
		</tr>
		
		<% if(list_all_workers != null){ for (User worker : list_all_workers) { %>
		<tr>
			<td><%= worker.getId() %></td>
			<td><%= worker.getUsername() %></td>
			<td><a href="DeleteWorker?action=deleteWorkerById&id=<%= worker.getId() %>">Remove worker</a></td>
		</tr>
		<% }} %>
		
	</table>
	
	
	<br>
	<span style="color: green; font-size: 20px;">${requestScope.successMsg}</span>
	<span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>
	
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>
