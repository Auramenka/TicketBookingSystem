<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Регистрация</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f3f4f6;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .registration-form {
      background-color: #ffffff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 400px;
    }
    .registration-form h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
    }
    .form-group input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .form-group input:focus {
      border-color: #007bff;
      outline: none;
    }
    .btn {
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      border: none;
      border-radius: 4px;
      color: #ffffff;
      font-size: 16px;
      cursor: pointer;
    }
    .btn:hover {
      background-color: #0056b3;
    }
    .error-message {
      color: red;
      font-size: 12px;
      margin-top: 5px;
    }
    .footer {
      text-align: center;
      margin-top: 15px;
    }
  </style>
</head>
<body>

<div class="registration-form">
  <h2>Регистрация</h2>

  <form action="/register" method="post">
    <div class="form-group">
      <label for="username">Имя пользователя</label>
      <input type="text" id="username" name="username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required>
      <% if (request.getAttribute("usernameError") != null) { %>
      <div class="error-message"><%= request.getAttribute("usernameError") %></div>
      <% } %>
    </div>
    <div class="form-group">
      <label for="email">Электронная почта</label>
      <input type="email" id="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
      <% if (request.getAttribute("emailError") != null) { %>
      <div class="error-message"><%= request.getAttribute("emailError") %></div>
      <% } %>
    </div>
    <div class="form-group">
      <label for="password">Пароль</label>
      <input type="password" id="password" name="password" value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>" required>
      <% if (request.getAttribute("passwordError") != null) { %>
      <div class="error-message"><%= request.getAttribute("passwordError") %></div>
      <% } %>
    </div>
    <button type="submit" class="btn">Зарегистрироваться</button>
  </form>
  <div class="footer">
    <p>Уже есть аккаунт? <a href="login.jsp">Войти</a></p>
  </div>
</div>

</body>
</html>
