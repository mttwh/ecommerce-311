<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="../jspf/header.jspf" />

	<div>
		<ul class="main-nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="category">Go Shopping</a></li>
			<li><a href="cart">Shopping Cart</a></li>
			<li class="active-link"><a href="#">Create Account</a></li>
			<li><a href="display">About us</a></li>
                        <li><a href="wishlist">Wishlist</a></li>
			
		</ul>
	</div>
	<br /><br />
	<hr>
	<h2>Create customer account below!</h2>
	<div id="login-form">
		<form action="register" method="POST">
			<p>Name</p>
			<input type="text" name="customerName">
			<br />
			<p>Email</p>
			<input type="text" name="customerEmail">
			<br />
			<p>Username</p>
			<input type="text" name="customerUsername">
			<br />
			<p>Password</p>
			<input type="password" name="customerPassword">
			<br /><br />
			<input id="button" type="submit" value="Register">
		</form>
	</div>