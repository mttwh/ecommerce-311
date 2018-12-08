<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<jsp:include page="../jspf/header.jspf" />
<c:set var="balance" value="${cartTotal}"/>
<h2>Checkout page</h2>

<table>
	<tr>
		<th>Product</th>
		<th>Quantity</th>
		<th>Item Total</th>
	</tr>
	<c:forEach var="item" items="${shoppingCart.cartItems}" varStatus="iter">
		<c:set var="product" value="${item.product}"/>
		<tr>
			<td>${product.productName}</td>
			<td>${item.quantity}</td>
			<td>$${item.itemTotal}</td>
		</tr>
	</c:forEach>
</table>
<span id="checkout-confirm-below">Confirm customer details below</span>
<div id="checkout-balance-display">
	<span>Balance: $${balance}</span>
</div>
<c:set var="customer" value="${customer}"/>
<div id="checkout-confirm-form">
	<form action="confirmation" method="post">
		Customer Username: <input type="text" name="checkout-confirm-username" value="${customer.customerUsername}" readonly>
		<br /><br />
		Customer Name: <input type="text" name="checkout-confirm-name" value="${customer.customerName}" readonly>
		<br /><br />
		Customer Email: <input type="text" name="checkout-confirm-email" value="${customer.customerEmail}" readonly>
		<br /><br />
		<%--<span class="smallText">Please enter your phone number and address below</span>
		<hr><br />
		Customer Phone: <input type="text" name="checkout-confirm-phone">
		<br /><br />
		Customer Address: <input type="text" name="checkout-confirm-address">
		<br /><br /> --%>
		<input class="button" type="submit" value="Complete Checkout">
	</form>
</div>
<div id="checkout-confirm-instructions">
	<strong>Order Pickup Instructions</strong>
	<br />
	<p>When you complete the checkout process you will be given an order number and sent an email confirmation.</p>
	<p>Please pick up your order at the store at the time you have selected.</p>
	<p>Payment can be made at the store. Order can be completed using your order number.</p>
</div>
