<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../jspf/header.jspf" />

	<div>
		<ul class="main-nav">
			<li><a href="index.jsp">Home</a></li>
			<li class="active-link"><a href="#">Go Shopping</a></li>
			<li><a href="cart">Shopping Cart</a></li>
			<li><a href="register">Create Account</a></li>
			<li><a href="display">About us</a></li>
                        <li><a href="wishlist">Wishlist</a></li>
		</ul>
	</div>
	<br /><br />
	<hr>
	<%-- get selected category and set it to a variable --%>
	<c:set var="selectedCategory" value="${selectedCategory.categoryName}"/>
	<div class="selectedCategoryTitle">
		<h2>${selectedCategory}</h2>
	</div>
	<div class="product-rows">
		<table class="product-table">
			<tr>
				<th>Product Image</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Add to Cart</th>
                                <th>Add to Wishlist</th>
			</tr>
			<%-- Receive list of products relevant to the selected category 
			and display them on the screen - product list received from ControllerServlet doGet method --%>
			<c:forEach var="product" items="${categoryProductList}">
		
				<tr>
					<td><img class="product-image" 
							 src="img/${product.productName}.png"
							 alt="${product.productName}">
					</td>
					<td>${product.productName}</td>
					<td>${product.productPrice}</td>
					<td>
						<form action = "addToCart" method="POST">
							<input type="hidden" name="productName" value="${product.productName}">
							<input class="button" type="submit" name="addToCart" value="Add To Cart">
						</form>
					</td>
                                        <td>
						<form action = "addToWishlist" method="POST">
							<input type="hidden" name="productName" value="${product.productName}">
							<input class="button" type="submit" name="addToWishlist" value="Add To Wishlist">
						</form>
					</td>
				</tr>

			</c:forEach>
		</table>
	</div>