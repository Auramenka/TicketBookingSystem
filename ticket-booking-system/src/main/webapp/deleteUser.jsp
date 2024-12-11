<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  UserDto user = (UserDto) request.getAttribute("user");
%>
<html>
<head>
    <title>Удаление пользователя</title>
</head>
<body>
<h2>Вы уверены, что хотите удалить пользователя?</h2>
<p><strong>Имя пользователя:</strong><%= user.getUsername()%>></p>
<p><strong>Электронная почта:</strong><%= user.getEmail()%></p>
<form action="/deleteUser" method="post">
  <input type="hidden" name="id" value="<%= user.getId()%>">
  <button type="submit">Удалить</button>
  <a href="/events">Отмена</a>
</form>
</body>
</html>