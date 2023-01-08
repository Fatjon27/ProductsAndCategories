<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <h1>Home Page</h1>
    <hr>
    <a href="/products/new" >New Product</a>
    <hr>
    <a href="/categories/new" >New Category</a>
    <hr>
    <p>Products</p>
    <ul>
        <c:forEach items="${products}" var="product">
            <li><a href="/products/${product.id}"><c:out value="${product.name}"/></a> </li>
        </c:forEach>
    </ul>
    <p>Categories</p>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li><a href="/categories/${category.id}"><c:out value="${category.name}"/></a> </li>
        </c:forEach>
    </ul>
</body>
</html>