<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  UserDto userDto = (UserDto) request.getAttribute("userDto");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Редактирование профиля</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }
    .container {
      max-width: 500px;
      margin: auto;
      background: white;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #333;
    }
    label {
      display: block;
      margin: 10px 0 5px;
      color: #555;
    }
    input[type="text"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    input[type="submit"] {
      background-color: #28a745;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      width: 100%;
      font-size: 16px;
      margin-top: 10px;
    }
    input[type="submit"]:hover {
      background-color: #218838;
    }
    .back-link {
      display: inline-block;
      margin-top: 10px;
      text-decoration: none;
      color: #007BFF;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Редактирование профиля</h2>
  <form action="editProfile" method="post">
    <input type="hidden" name="id" value="<%= userDto.getId() %>"/>

    <label for="username">Имя пользователя:</label>
    <input type="text" name="username" value="<%= userDto.getUsername() %>" required/>

    <label for="email">Электронная почта:</label>
    <input type="text" name="email" value="<%= userDto.getEmail() %>" required/>

    <input type="submit" value="Обновить информацию"/>
    <a href="/events" class="back-link">Назад</a>
  </form>
</div>
</body>
</html>
