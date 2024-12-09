<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        .button {
            display: inline-block;
            padding: 10px 15px;
            margin-top: 20px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            color: #007BFF;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Профиль пользователя</h1>
    <%
        UserDto user = (UserDto) request.getAttribute("userDto");
        if (user != null) {
    %>
    <p><strong>Имя пользователя:</strong> <%= user.getUsername() %></p>
    <p><strong>Email:</strong> <%= user.getEmail() %></p>
    <a href="editProfile?id=<%= user.getId() %>" class="button">Редактировать</a>
    <%
    } else {
    %>
    <p>Пользователь не найден.</p>
    <%
        }
    %>
    <a href="/events" class="back-link">Назад</a>
</div>
</body>
</html>