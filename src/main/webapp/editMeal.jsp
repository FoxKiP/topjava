<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.02.2020
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal Edit</title>
    <style>
        dd {
            margin-left: 0px;
            margin-top: 3px;
        }
        dt {
            margin-top: 5px;
        }
        button {
            margin-bottom: 3px;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meal</h2>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" value="${meal.id}" name="id">
        <dl>
        <dt>Date</dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
        <dt>Description</dt>
        <dd><input type="text" value="${meal.description}" name="description"></dd>
        <dt>Calories</dt>
        <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>    
        <button type="submit">Save</button><br/>
        <button onclick="window.history.back()">Cancel</button><br/>
    </form>
</section>
</body>
</html>
