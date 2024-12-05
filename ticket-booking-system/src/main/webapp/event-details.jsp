<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page import="com.innowise.ticketbookingsystem.model.Role" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Детали мероприятия</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .tabs {
            display: flex;
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
            justify-content: center;
            margin: 20px;
        }
        .event {
            border: 1px solid #ddd;
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            display: flex;
            align-items: center;
            width: 80%;
            box-sizing: border-box;
        }
        .event img {
            width: 300px;
            height: auto;
            margin-right: 20px;
        }
        .event-details {
            text-align: left;
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
        .spacing {
            margin-top: 20px;
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
    </style>
</head>
<body>
<%
    EventDto event = (EventDto) request.getAttribute("event");
    if (event != null) {
%>
<div class="event-container">
    <div class="event">
        <img src="<%= event.getPhoto() %>" alt="<%= event.getName() %>"/>
        <div class="event-details">
            <h1><%= event.getName() %></h1>
            <span class="event-date"><strong>с <%= event.getDateStart() %> по <%= event.getDateEnd() %></strong></span>
            <p><%= event.getDescription() %></p>
            <a href="/events" class="more-button">Назад к событиям</a>
            <a href="/seance?eventId=<%= event.getId() %>" class="more-button">Просмотр доступных сеансов</a>

            <div class="spacing"></div>

            <div class="header-title-container">
                <%
                    UserDto user = (UserDto) session.getAttribute("user");
                    if (user != null && Role.ADMIN == user.getRole()) {
                %>
                <a href="addSeance.jsp?eventId=<%= event.getId() %>" class="login-button">Добавить сеанс</a>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>

<%
} else {
%>
<p>Мероприятие не найдено.</p>
<%
    }
%>
</body>
</html>