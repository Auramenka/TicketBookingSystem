<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EventDto eventDto = (EventDto) request.getAttribute("eventDto");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Удаление мероприятия</title>
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
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        strong {
            color: #000;
        }
        button {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #c0392b;
        }
        a {
            margin-left: 15px;
            text-decoration: none;
            color: #3498db;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Вы уверены, что хотите удалить следующее мероприятие?</h2>
    <p><strong>Название:</strong> <%= eventDto.getName() %></p>
    <p><strong>Описание:</strong> <%= eventDto.getDescription() %></p>
    <form action="/deleteEvent" method="post">
        <input type="hidden" name="id" value="<%= eventDto.getId() %>">
        <button type="submit">Удалить</button>
        <a href="/events">Отмена</a>
    </form>
</div>
</body>
</html>
