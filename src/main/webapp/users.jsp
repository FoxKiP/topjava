<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<table border="1", cellspacing="0", cellpadding="8">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Enabled</th>
        <th>Registration date</th>
    </tr>
    </thead>
    <c:forEach var="user" items="${users}">
        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.roles}</td>
            <td>${user.enabled}</td>
            <td>${fn:formatDate(user.registered)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>