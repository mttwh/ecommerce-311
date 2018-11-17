<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="WEB-INF/jspf/header.jspf" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Grocery Store Welcome Page</title>
		<link rel="stylesheet" href="styles/styles.css">
	</head>
	<body>
		<div>
			<ul class="main-nav">
				<li class="active-link"><a href="#">Home</a></li>
				<li><a href="category">Go Shopping</a></li>
				<li><a href="cart">Shopping Cart</a></li>
				<li><a href="register">Create Account</a></li>
			</ul>
		</div>
		<br /><br />
		<hr>
		<div class="login-form">
			<form action="LoginController" method="POST">
				<p>Username <input type="text" name="customerUsername"></p>
				<br /><br />
				<p>Password <input type="password" name="customerPassword"></p>
				<br /><br />
				<input id = "button" type="submit" value="Login">
				<p>Not yet registered? <a href="register">Click Here</a></p>
				<p>Admins click <a href="adminLogin">Here</a> to login</p>
			</form>
		</div>
		<div class="category-rows">
			<table class="category-table">
				<tr>
					<th>Category Name</th>
					<th>Category Image</th>
				</tr>
				<%-- receive categories from application context (ControllerServlet init() method) --%>
				<c:forEach var="category" items="${categories}">
			
					<tr>
						<td>${category.categoryName}</td>
						<td><a href="category?${category.categoryName}">
								<img id="category-image" src="img/${category.categoryName}.png"
								alt="${category.categoryName}">
							</a>
						</td>
					</tr>

				</c:forEach>
			</table>
		</div>
	</body>
</html>