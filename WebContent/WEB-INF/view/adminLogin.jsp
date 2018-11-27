<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<jsp:include page="../jspf/header.jspf" />

	<h2>Admins login below</h2>
	<div id="login-form">
		<form action="adminLogin" method="post">
			<p>Username: <input type="text" name="adminUsername"></p>
			<br /><br />
			<p>Password: <input type="password" name="adminPassword"></p>
			<br /><c:if test="${adminLoginMessage == 'fail'}">
				  	<span id="fail-popup">Login unsuccessful</span>
				  </c:if>
			<br /><input id="button" type="submit" value="Admin Login">
		</form>
	</div>
