<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../jspf/header.jspf" />

<h2>Confirmation page</h2>
<c:set var="balance" value="${cartTotal}" />
<c:set var="order" value="${mostRecentOrder}" />
<c:set var="orderDetails" value="${orderDetails}" />

<p>Hello, ${order.customerUsername}! Your order number is ${order.orderId}.</p>
<p>We have sent an email confirmation to you at ${order.customerEmail}. Below is a summary of your order.</p>
<hr>
<c:forEach var="orderDetails" items="${orderDetails}">
	<p>${orderDetails.productName}: Quantity - ${orderDetails.quantity} Price - $${orderDetails.productPrice}</p>
</c:forEach>

<p><a href="index.jsp">Click Here</a> to return to the home page</p>