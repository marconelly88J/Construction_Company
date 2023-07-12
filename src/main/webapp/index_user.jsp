
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	User user_session = (User) request.getSession().getAttribute("user_session");
    if(user_session != null) { 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Machine Rent</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>

	<header>
		<h1>Machine Rent</h1>
		<form action="./Login" method="get">
			<input type="submit" name="action" value="logout">
		</form>
	</header>

	<div id="main">

		<div class="text">
			<h2><%= user_session.getUsername() %>, Welcome!</h2>
		</div>

		<hr>
		<br> 

		<form action="UserMachineController" method="get" id="find-book">
			<button type="submit" class="btn" name="action" value="listAllMachines">RENT MACHINE</button> <br>
		</form>
		
		<form action="UserRentController" method="get">
			<button type="submit" class="btn" name="action" value="rentedMachines">RENTED MACHINES</button>
		</form>
		
		
		<hr>
		
		<span style="color: red; font-size: 20px;">${requestScope.errorMsg}
			${requestScope.error}</span>

	</div>

</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
