<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="WEB-INF/jspf/header.jspf" />

<div id="hello-user"></div>
<div id="user-instructions"></div>
<c:if test="${checkoutLoginMessage == 'Login'}">
	<script>
		var welcome = "<span id='fail-popup'>You Must be logged in to checkout! Please login below and try again.</span>"
		document.getElementById("user-instructions").innerHTML=welcome;
	</script>
</c:if>
<c:set var="customer" scope="session" value="${customer}"/>
<c:choose>
	<c:when test="${customer.customerUsername == null}">
		<script>
			var welcome = "Login below to get started";
			document.getElementById("hello-user").innerHTML=welcome;
		</script>
	</c:when>
	<c:otherwise>
		<script>
			var welcome = "${customer.customerUsername}";
			document.getElementById("hello-user").innerHTML="Welcome " + welcome;
		</script>
	</c:otherwise>
</c:choose>
	<hr>
	<div>
		<ul class="main-nav">
			<li class="active-link"><a href="#">Home</a></li>
			<li><a href="cart">Shopping Cart</a></li>
			<li><a href="register">Create Account</a></li>
			<li><a href="display">About us</a></li>		
			<li><a href="wishlist">Wishlist</a></li>
		</ul>
	</div>
<div id="search-box">
	<form id="search-bar" action="search" method="POST">
    	<input type="text" placeholder="Search.." name="searchedProduct">
     	    <button type="submit">Submit</button>
	</form>
</div>
	<hr>
	<div id="login-form">
		<form id="login-area" action="login" method="POST">
			<p>Username <br /> <input type="text" name="customerUsername"></p>
			<br />
			<p>Password <br /> <input type="password" name="customerPassword"></p>
			<div>
				<br /><c:choose>
				  	<c:when test="${message == 'success'}">
				  		<span id="success-popup">Login successful!</span>
				  	</c:when>
				  	<c:when test="${message == 'fail'}">
				  		<span id="fail-popup">Login unsuccessful, try again.</span>
				  	</c:when>
				  </c:choose>
		</div>
		<input id = "button" type="submit" value="Login">
		<p>Not yet registered? <a href="register">Click Here</a></p>
		<p>Admins click <a href="adminLogin">Here</a> to login</p>
	</form>
</div>
<c:if test="${customer.customerUsername != null}">
	<script>
		var logoutForm = "<p>Welcome, ${customer.customerUsername}. You have successfully logged in!</p>" +
						"<form id='logout-area' action='logout' method='post'>" +
						"<input type='submit' name='logoutButton' value='Logout'></form>";
		document.getElementById("login-form").innerHTML=logoutForm;
	</script>
</c:if>
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