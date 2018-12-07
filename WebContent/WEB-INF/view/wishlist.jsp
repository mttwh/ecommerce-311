<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../jspf/header.jspf" />

	<div>
		<ul class="main-nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="category">Go Shopping</a></li>
			<li><a href="cart">Shopping Cart</a></li>
			<li><a href="register">Create Account</a></li>
			<li><a href="display">About us</a></li>
                        <li class="active-link"><a href="wishlist">Wishlist</a></li>
		</ul>
	</div>
	<br /><br />
	<hr>
	
	<div class="product-rows">
		<table class="product-table">
			<tr>
				
				<th>Product </th>
				<th>Product Name</th>
				<th>Product Price</th>
                                <th>Add to Cart</th>
                                <th>Remove</th>
			</tr>
			<%-- Receive list of wishlist items
			 --%>
				<c:forEach var="product" items="${wishlist}">
					<tr>
						<td>
                                                <img src="img/${product.productName}.png" 
						 alt="${product.productName}">
                                                </td>
                                                <td>
                                                ${product.productName}
                                                </td>
						<td>${product.productPrice}
						</td>
                                                <td>
						<form action = "moveToCart" method="POST">
							<input type="hidden" name="productName" value="${product.productName}">
							<input class="button" type="submit" name="moveToCart" value="Add To Cart">
						</form>
                                                </td>
                                                <td>
                                                <form action = "removeFromWishlist" method="POST">
							<input type="hidden" name="productName" value="${product.productName}">
							<input class="button" type="submit" name="removeFromWishlist" value="Remove">
						</form>
                                                </td>
                                                
					</tr>

				</c:forEach>
                                        
                                	
		</table>
	</div>