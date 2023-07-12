

<%@page import="dto_resource.DTO_RentedMachines"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	User user_session = (User) request.getSession().getAttribute("user_session");
	ArrayList<DTO_RentedMachines> rented_machines = (ArrayList<DTO_RentedMachines>) request.getAttribute("rented_machines");
	
	if(user_session != null) {
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rented Machines</title>
<link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<body>

	<h1>Machines rented by <%= user_session.getUsername() %></h1>
	<hr>	
	<table border="1" style="width: 50%;">
	
		<tr>
			<th>Worker ID</th>
			<th>Machine ID</th>
			<th>Units rented</th>
			<th>Rent start</th>
			<th>Rent end</th>
			<th>action</th>
			<th>action</th>
		</tr>
		
		<% int deleteId = 0; if(rented_machines != null){ for(DTO_RentedMachines dto : rented_machines) { %>
		<tr>
			<td><%= dto.getWorker_id() %></td>
			<td><%= dto.getMachine_id() %></td>
			<td><%= dto.getUnits() %></td>
			<td><%= dto.getRent_start() %></td>
			<td><%= dto.getRent_end() %></td>
			<td>
				<form action="UserRentController" method="post">
            		<input type="hidden" name="workerId" value="<%= dto.getWorker_id() %>">
            		<input type="hidden" name="machineId" value="<%= dto.getMachine_id() %>">
            		<input type="hidden" name="rentTime" value="<%= java.time.LocalDate.now() %>">
            		
            		<% if (dto.getRent_end() == null) { %>
            		<button type="submit" name="action" value="finishRent">Finish renting</button>
            		<% } else { %>
            		<b>Rent Finished</b>
            		 <% } %>
        		</form>
			</td>
			
			<td>
				<form action="UserRentController" method="post">
					<input type="hidden" name="workerId" value="<%= dto.getWorker_id() %>">
            		<input type="hidden" name="machineId" value="<%= dto.getMachine_id() %>">
					<% if (dto.getRent_end() != null) { %>
					<button type="submit" name="action" value="deleteHistory">Delete history</button>
					<% } %>
				</form>
			</td>
			
		</tr>
		<% } } %>
	</table>
	
	<hr>
	<a href="index_user.jsp">Back to Home</a>
	

</body>
</html>

<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
