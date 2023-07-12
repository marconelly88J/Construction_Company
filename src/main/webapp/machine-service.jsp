<%@page import="model.Machine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Admin admin_session = (Admin) request.getSession().getAttribute("admin_session");
	ArrayList<Machine> list_all_machines = (ArrayList<Machine>) request.getAttribute("list_all_machines");
	
	
 	if(admin_session != null) { 
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Machine Service</title>
<link rel="stylesheet" href="css/machine_service.css">
</head>
<body>

	<h1>ADMIN Machine Service Database </h1>	
	
	
	<hr>
	
	<div id="main">
	
	<% 	
		if (list_all_machines != null) {
    	for (Machine machine : list_all_machines) {
	%>
    <div id="machine-container">
        <div class="machine-cover">
            <img alt="machine cover" src="<%= machine.getImg_path() %>">
        </div>
        <br>
        <div class="machine-info">
            <div>Machine type: <b><%= machine.getMachine_type() %></b></div> 
            
            <% 
				String machine_status = (machine.getStock() == 0) ? "UNAVAILABLE" : "AVAILABLE";
   				String status_color = (machine.getStock() == 0) ? "red" : "green"; 
   			%>
   			
   			<div>Units available: <b><%= machine.getStock() %></b></div> <br>
   			
			Status: <div style="color: <%= status_color %>"><%= machine_status %></div> <br>
            <div><b>Machine ID:</b> <%= machine.getMachine_id() %></div><br>
            
            <form action="MachineService" method="post">
                <input style="width:105px;" type="text" name="newStock" placeholder="New stock">
                <input type="hidden" name="action" value="updateStock">
                <input type="hidden" name="id" value="<%= machine.getMachine_id() %>">
                
                <button type="submit">Update machine stock</button>
            </form>
            
          		
                <span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
            	
            <br><br>
            
            <a href="MachineService?action=deleteMachine&id=<%= machine.getMachine_id()%>">REMOVE MACHINE</a>
            
            <hr>
        </div>
    </div>
		<% }} %>
	
	</div>
	
	<br>
	<span style="color: green; font-size: 20px;">${requestScope.delSuccess}</span>
	<span style="color: red; font-size: 20px;">${requestScope.delError}</span>
	<br>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>

</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>
