<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../jspf/header.jspf" />

<div id="admin-return">
	<form action="admin" method="post">
		<h3>Click below to return to admin page.</h3>
		<input type="submit" value="Return">
	</form>
</div>
<h2>Update the selected product below</h2>
<c:set var="product" scope="request" value="${productToUpdate}" />
<div id="update-product-form">
	<form action="updateProduct" method="post">
		<h4>Submit new product details below.</h4>
		<p>
			Product Name: <span class="smallLightText">(${product.productName})</span>
		</p>
		<input type="text" name="newProductName"
			value="${product.productName}">
		<p>
			Product Description: <span class="smallLightText">(${product.productDescription})</span>
		</p>
		<input type="text" name="newProductDescription"
			value="${product.productDescription}">
		<p>
			Product Price: <span class="smallLightText">($${product.productPrice})</span>
		</p>
		<input type="text" name="newProductPrice"
			value="${product.productPrice}">
		<p>
			Product Category: <span class="smallLightText">(${product.categoryName})</span>
		</p>
		<select name="newProductCategory">
			<option value="${product.categoryName}" disabled selected hidden>${product.categoryName}</option>
			<c:forEach var="category" items="${categories}">
				<option>${category.categoryName}</option>
			</c:forEach>
		</select><br />
		<br /> <input type="hidden" name="oldProductName"
			value="${product.productName}"> <input type="submit"
			name="updateProductButton" value="Submit">

	</form>
</div>