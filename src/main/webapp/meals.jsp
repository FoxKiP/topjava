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
    <title>Meals List</title>
    <style>
        .normal {
            color: green;
        }
        .excess {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="1" cellspacing="1">
    <c:forEach var="mealTo" items="${mealsTo}">
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
