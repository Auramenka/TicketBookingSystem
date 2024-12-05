<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль пользователя</title>
</head>
<body>
<h1>Профиль пользователя</h1>
<%
    UserDto user = (UserDto) request.getAttribute("user");
    if (user != null) {
%>
<p><strong>Имя пользователя:</strong> <%= user.getUsername() %></p>
<p><strong>Email:</strong> <%= user.getEmail() %></p>
<%
} else {
%>
<p>Пользователь не найден.</p>
<%
    }
%>
<a href="/events">Назад</a>
</body>
</html>
