<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.02.2020
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <c:forEach var="mealTo" items="${mealsTo}">
        <c:if test="${mealTo.excess}">
            <c:set var="color" value="#ff0000"/>
        </c:if>
        <c:if test="${!mealTo.excess}">
            <c:set var="color" value="#00ff00"/>
        </c:if>
        <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <tr bgcolor="${color}">
            <td>${mealTo.id}</td>
            <td>${parsedDate}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
