<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDto user = (UserDto) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Удаление пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #d9534f;
        }
        p {
            font-size: 16px;
            margin: 10px 0;
        }
        button {
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #c9302c;
        }
        a {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            color: #5bc0de;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Вы уверены, что хотите удалить пользователя?</h2>
    <p><strong>Имя пользователя:</strong> <%= user.getUsername() %></p>
    <p><strong>Электронная почта:</strong> <%= user.getEmail() %></p>
    <form action="/deleteUser" method="post">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <button type="submit">Удалить</button>
        <a href="/events">Отмена</a>
    </form>
</div>
</body>
</html>