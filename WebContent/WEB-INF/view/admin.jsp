<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../jspf/header.jspf" />

	<h2>Welcome to the admin page</h2>
	<p>Click <a href="index.jsp">Here</a> to return to the home page.</p>
	<p>This is the page where store managers can add, remove, or update products from the site</p>
	<hr>
	
	<div id="login-form">
		<form action="addProduct" method="POST">
			<p>Product Name:</p>
			<input type="text" name="productName">
			<br />
			<p>Product Description:</p>
			<input type="text" name="productDescription">
			<br />
			<p>Product Price:</p>
			<input type="number" step="0.01" min="0" name="productPrice">
			<br />
			<%-- get category names from application context variable ${categories} and
			add them to a dropdown list so you can select a category --%>
			<p>Select Category:</p>
			<select name="categorySelection">
				<c:forEach var="category" items="${categories}">
					<option>${category.categoryName}</option>
				</c:forEach>
			</select>
			<br /><br />
			<input id = "button" type="submit" name="addProductButton" value="Add Product">
		</form>
	</div>
	<div class="viewProductTable">
		<table class="productTable">
			<tr>
				<th>Product Name</th>
				<th>Product Description</th>
				<th>Product Price</th>
				<th>Update Product</th>
			</tr>
			<c:forEach var="product" items="${allProductList}">
				<tr>
					<td>${product.productName}</td>
					<td>${product.productDescription}</td>
					<td>${product.productPrice}</td>
					<td>
						<div id="productButtons">
							<form action="deleteProduct" method="post">
								<input type="hidden" name="productToDelete" value="${product.productName}">
								<input type="submit" name="deleteButton" value="Delete"
								  onclick="return confirm('Are you sure?')">
							</form>
							<form action="updateProduct" method="post">
								<input type="hidden" name="productToUpdate" value="${product.productName}">
								<input type="submit" name="updateButton" value="Update Product"
								  onclick="return confirm('Are you sure?')">
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="addProductMessage"></div>
	<c:set var="product" scope="request" value="${product}"/>
	<c:choose>
		<c:when test="${addMessage == 'exists'}">
			<script>
				var message = "<span id='fail-popup'>Product already exists</span>";
				document.getElementById("addProductMessage").innerHTML=message;
			</script>
		</c:when>
		<c:when test="${addMessage == 'empty-fields'}">
			<script>
				var message = "<span id='fail-popup'>All fields are required</span>";
				document.getElementById("addProductMessage").innerHTML=message;
			</script>
		</c:when>
		<c:when test="${addMessage == 'invalid-price'}">
			<script>
				var message = "<span id='fail-popup'>Product price is invalid. Must be number between 0 and 500.</span>";
				document.getElementById("addProductMessage").innerHTML=message;
			</script>
		</c:when>
		<c:when test="${product.productName == null}">
			<script>
				var message = "<span class='smallText'>Fill in above form to add product to inventory.</span>";
				document.getElementById("addProductMessage").innerHTML=message;
			</script>
		</c:when>
		<c:otherwise>
			<script>
				var message = "<span id='success-popup'>You have added ${product.productName} to the ${product.categoryName} category.</span>";
				document.getElementById("addProductMessage").innerHTML=message;
			</script>
		</c:otherwise>
	</c:choose>