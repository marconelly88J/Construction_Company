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
<title>New Machine</title>
<link rel="stylesheet" type="text/css" href="css/input.css">
</head>
<body>

	<h1>Add new machine</h1>
	<hr>

	<form action="addNewMachine" method="post">
		
		Machine type: <br>
		<input type="text" name="machineType" > <br><br>
		
		Stock: <br>
		<input type="text" name="stock" > <br><br>
		
		Machine image name: <br>
		<input type="text" name="img_path"> <br><br>
		
		<button type="submit" name="action" value="addNewMachine">ADD MACHINE</button> 
	</form>
	<br><br>
	<span style="color: green; font-size: 20px;">${requestScope.successMsg}</span>
	<span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>
	
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>
