<%@page import="model.Machine"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	
	ArrayList<Machine> list_all_machines = (ArrayList<Machine>) request.getAttribute("list_all_machines");
	User user_session = (User) request.getSession().getAttribute("user_session");

 	if(user_session != null) { 
 
 %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Machines</title>
<link rel="stylesheet" href="css/list_machines.css">
</head>
<body>

	<header>
		<h1>Machine List</h1>
	</header>
	<a href="index_user.jsp" style="margin-left: 580px;">Back to Home</a>
	<hr>
	
	<div id="main">
	<% if(list_all_machines != null) { for(Machine machine : list_all_machines) { %>
	<div id="machine-container">
		
		<div class="machine-cover">
			<img alt="Machine cover" src="<%= machine.getImg_path() %>">
		</div>
		
		<div class="machine-info">
		
			<div>MACHINE TYPE: <b><%= machine.getMachine_type() %></b></div>
			<div><b>STOCK:</b> <%= machine.getStock() %></div>
			
			<% 
				String machine_status = (machine.getStock() == 0) ? "UNAVAILABLE" : "AVAILABLE";
   				String status_color = (machine.getStock() == 0) ? "red" : "green"; 
   			%>
			<div style="color: <%= status_color %>"><%= machine_status %></div>
			<br>
			
			<form action="UserRentController" method="post">
				<input type="number" name="units" placeholder="units" min="1" max="<%= machine.getStock() %>">
				<input type="hidden" name="machineStock" value="<%= machine.getStock() %>">
				<input type="hidden" name="workerId" value="<%= user_session.getId() %>">
				<input type="hidden" name="machineId" value="<%= machine.getMachine_id() %>">
				<input type="hidden" name="rentTime" value="<%= java.time.LocalDate.now() %>">
				<button type="submit" name="action" value="rentMachine">Rent</button>
			</form>
			<span style="font-size: 20px;">${requestScope.rentMsg}</span>
			
		</div>
		
	</div>
	
	<hr>
	
	<% } } %>
	
	
	<a href="index_user.jsp" style="margin-left: 580px;">Back to Home</a>
	
	</div>
	
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
