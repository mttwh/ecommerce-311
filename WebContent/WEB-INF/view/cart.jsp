 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../jspf/header.jspf" />

	<div>
		<ul class="main-nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="category">Go Shopping</a></li>
			<li class="active-link"><a href="cart">Shopping Cart</a></li>
			<li><a href="register">Create Account</a></li>
			<li><a href="display">About us</a></li>
                        <li><a href="wishlist">Wishlist</a></li>
		</ul>
	</div>
	<br /><br />
	<hr>
	
	<h2>Shopping Cart</h2>
	<table>
		<tr>
			<th>Product</th>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		
		<c:forEach var="item" items="${shoppingCart.cartItems}" varStatus="iter">
			<c:set var="product" value="${item.product}"/>
			<tr>
				<td>
					<img src="img/${product.productName}.png" 
						 alt="${product.productName}">
				</td>
				<td>
					${product.productName}
				</td>
				<td>
					$${item.itemTotal}
					<br />
					<p class="smallText">$${product.productPrice} / item </p>
				</td>
				<td>
					${item.quantity} items
					<div id="quantityButtons">
						<form action="updateQuantity" method="post">
							<input type="hidden" name="productNameToUpdate" value="${product.productName}">
							<input type="submit" name="increaseQuantity" value="Increase Quantity"
							  onclick="return confirm('Are you sure you want to increase the quantity of ${product.productName} in your cart?')"><br />
							<input type="submit" name="decreaseQuantity" value="Decrease Quantity"
							  onclick="return confirm('Are you sure you want to decrease the quantity of ${product.productName} in your cart?')"><br />
							<input type="submit" name="removeItem" value="Remove Item"
							  onclick="return confirm('Are you sure you want to remove ${product.productName} from your cart?')">
						</form>
					</div>
				</td>
				
			</tr>
		</c:forEach>	
	</table>