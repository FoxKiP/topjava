<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.02.2020
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://topjava/javawebinar.ru/functions"%>
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
        form {
            margin-bottom: 3px;
        }
    </style>
</head>
<body>
<section>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<form action="meals" method="get">
    <input type="hidden" value="create" name="action">
    <button type="submit">Create Meal</button>
</form>
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Operation</th>
        </tr>
    </thead>
    <c:forEach var="meal" items="${mealsTo}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${fn:dateToString(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <form action="meals" method="get">
                    <input type="hidden" value="delete" name="action">
                    <input type="hidden" value="${meal.id}" name="id">
                    <button type="submit">delete</button>
                </form>
                <form action="meals" method="get">
                    <input type="hidden" value="update" name="action">
                    <input type="hidden" value="${meal.id}" name="id">
                    <button type="submit">update</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>
