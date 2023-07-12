<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Construct | Log in</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

	<div id="login-id">
	<h1>Construct D.O.O.</h1>
	
	
	<br>
	
	<form action="./Login" method="post">
		<span style="font-size: 20px;">Username:</span> <br>
		<input type="text" style="width: 300px; height: 30px;" required name="username"><br>
		
		<br><br>
		<span style="color: blue;">
		* Log in with existing user from database <br>
		* For creating new worker in database, Log in with New Username <br>
		* New Worker must not contain 'admin' in Username <br>
		* Only one admin credentials: admin1234<br><br>
		</span>
		
		<input class="btn" type="submit" name="action" value="Login">
	</form>
	<br><br>
	<span style="color: red;font-size: 20px;">${requestScope.msg}</span>
	</div>
	

</body>
</html>