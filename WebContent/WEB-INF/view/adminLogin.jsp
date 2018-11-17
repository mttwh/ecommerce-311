<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
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
		<form action="adminLogin" method="post">
			<p>Username: <input type="text" name="adminUsername"></p>
			<br /><br />
			<p>Password: <input type="password" name="adminPassword"></p>
			<br /><c:if test="${adminLoginMessage == 'fail'}">
				  	<span id="fail-popup">Login unsuccessful</span>
				  </c:if>
			<input id="button" type="submit" value="Admin Login">
		</form>
	</div>
</body>
</html>