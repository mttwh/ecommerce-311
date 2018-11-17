<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="../jspf/header.jspf" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login Page</title>
</head>
<body>
	<h2>Admins login below</h2>
	<div class="login-form">
		<form action="LoginController" method="POST">
			<p>Username: <input type="text" name="customerUsername"></p>
			<br /><br />
			<p>Password: <input type="password" name="customerPassword"></p>
			<br />
			<input id="button" type="submit" value="Admin Login">
		</form>
	</div>
</body>
</html>