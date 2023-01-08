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
    <title>Product Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
        <h1><c:out value="${product.name}"/></h1>
        <a href="/">Home</a>
        <hr>
        <h2>Categories :</h2>
        <ul>
            <c:forEach items="${categoriesOfProduct}" var="category">
                <li><c:out value="${category.name}"/> </li>
            </c:forEach>
        </ul>
        <hr>
        <form method="POST" action="/products/${product.id}">
        <label>Add Category: </label>
        <select id="categoryId" name="categoryId">
            <c:forEach items="${availableCategories}" var="category">
                <option value="${category.id}"><c:out value="${category.name}"/></option>
            </c:forEach>
        </select>
        <input type="submit" value="Add">
        </form>


</body>
</html>