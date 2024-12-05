<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Главная страница</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    .login-button {
      position: absolute;
      top: 20px;
      right: 20px;
      padding: 10px 20px;
      background-color: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      border: none;
    }
    .login-button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<h1>Список мероприятий</h1>
<div>
  <%
    List<EventDto> events = (List<EventDto>) session.getAttribute("events");
    if (events != null) {
      for (EventDto event : events) {
  %>
  <div class="event">
    <span><strong><%= event.getName() %></strong></span>
    <img src="<%= event.getPhoto() %>" alt="<%= event.getName() %>" />
  </div>
  <%
    }
      session.removeAttribute("events");
  } else {
  %>
  <p>Нет мероприятий для отображения</p>
  <%
    }
  %>
</div>

<a href="login.jsp" class="login-button">Войти</a>

</body>
</html>