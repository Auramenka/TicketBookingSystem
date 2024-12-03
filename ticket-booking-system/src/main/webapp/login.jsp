<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Вход</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <style>
    body {
      background-color: #f4f4f4;
      font-family: Arial, sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .login-container {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      width: 300px;
    }
    .login-container h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    .input-group {
      margin-bottom: 15px;
    }
    .input-group label {
      display: block;
      margin-bottom: 5px;
    }
    .input-group input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .btn {
      width: 100%;
      padding: 10px;
      background-color: #5cb85c;
      border: none;
      border-radius: 4px;
      color: white;
      font-size: 16px;
      cursor: pointer;
    }
    .btn:hover {
      background-color: #4cae4c;
    }
    .footer {
      text-align: center;
      margin-top: 15px;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>Вход</h2>
  <form action="login" method="post">
    <div class="input-group">
      <label for="username">Имя пользователя</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div class="input-group">
      <label for="password">Пароль</label>
      <input type="password" id="password" name="password" required>
    </div>
    <button type="submit" class="btn">Войти</button>
  </form>
  <div class="footer">
    <p><a href = "register.jsp">Зарегистрироваться</a></p>
  </div>
</div>
</body>
</html>