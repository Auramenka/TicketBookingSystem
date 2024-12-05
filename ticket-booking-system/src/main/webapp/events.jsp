<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.innowise.ticketbookingsystem.model.Role" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #007bff;
            color: white;
            padding: 20px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
        }
        .tabs {
            display: flex;
            justify-content: flex-start;
            flex-grow: 1;
        }
        .tab {
            margin-right: 20px;
            color: white;
            text-decoration: none;
            padding: 10px;
            border-radius: 5px;
        }
        .tab:hover {
            background-color: #0056b3;
        }
        .login-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .login-button:hover {
            background-color: #0056b3;
        }
        .event-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 20px;
        }
        .event {
            border: 1px solid #ddd;
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            width: calc(50% - 20px);
            box-sizing: border-box;
            display: flex;
            align-items: flex-start;
        }
        .event img {
            width: 150px;
            height: auto;
            display: block;
            margin-right: 10px;
        }
        .event-details {
            flex-grow: 1;
        }
        .event .event-date {
            display: block;
            margin-bottom: 5px;
        }
        .event .more-button {
            display: block;
            width: 100px;
            padding: 5px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 10px;
            text-align: center;
        }
        .event .more-button:hover {
            background-color: #0056b3;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            padding: 10px;
            margin: 0 5px;
            text-decoration: none;
            border: 1px solid #007bff;
            border-radius: 5px;
            color: #007bff;
        }
        .pagination a:hover {
            background-color: #007bff;
            color: white;
        }
        .header-title-container {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 20px 0;
        }
        .header-title {
            margin-right: 20px;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="tabs">
        <%
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
        %>
        <a href="?category=movies" class="tab">Кино</a>
        <a href="?category=concerts" class="tab">Концерты</a>
        <a href="?category=performances" class="tab">Спектакли</a>
        <%
        } else {
            if (Role.USER == user.getRole()) {
        %>
        <a href="myOrders.jsp" class="tab">Мои заказы</a>
        <a href="profile?userId=<%= user.getId() %>" class="tab">Профиль</a>
        <%
        } else if (Role.ADMIN == user.getRole()) {
        %>
        <a href="/manageEvents" class="tab">Управление мероприятиями</a>
        <%
                }
            }
        %>
    </div>
    <div class="login-button-container">
        <%
            if (user == null) {
        %>
        <a href="login.jsp" class="login-button">Войти</a>
        <%
        } else {
        %>
        <a href="logout" class="login-button">Выход</a>
        <%
            }
        %>
    </div>
</div>

<div class="header-title-container">
    <h1 class="header-title">Список мероприятий</h1>
</div>

<div class="event-container">
    <%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<EventDto> events = (List<EventDto>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
            for (EventDto event : events) {
                String dateStartFormatted = event.getDateStart() != null ? event.getDateStart().format(formatter) : "Нет даты начала";
                String dateEndFormatted = event.getDateEnd() != null ? event.getDateEnd().format(formatter) : "Нет даты окончания";
    %>
    <div class="event">
        <img src="<%= event.getPhoto() %>" alt="<%= event.getName() %>" />
        <div class="event-details">
            <span class="event-date"><strong>с <%= dateStartFormatted %> по <%= dateEndFormatted %></strong></span>
            <span><strong><%= event.getName() %></strong></span>
            <a href="eventDetail?id=<%= event.getId() %>" class="more-button">Подробнее</a>
            <%
                if (user != null && Role.ADMIN == user.getRole()) {
            %>
            <a href="editEvent?id=<%= event.getId() %>" class="more-button">Редактирование</a>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p>Нет мероприятий для отображения</p>
    <%
        }
    %>
</div>

</body>
</html>