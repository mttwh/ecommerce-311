<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../jspf/header.jspf" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>search hhvbdb</title>
</head>
<body>
<table>
<c>
<td>
					<form action = "Cart" method="POST">
							<input type="text" name="productName" value="${productBean.getProducts}">
							<input class="button" type="submit" name="Cart" value="">
						</form>
				</td>
				<c/>
				</table>>
</body>
</html>