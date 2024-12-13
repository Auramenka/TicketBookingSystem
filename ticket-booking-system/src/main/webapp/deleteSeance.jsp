<%@ page import="com.innowise.ticketbookingsystem.dto.SeanceDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SeanceDto seance = (SeanceDto) request.getAttribute("seance");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Удаление сеанса</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        .button {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
            text-decoration: none;
        }
        .button.cancel {
            background-color: #3498db;
        }
        .button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Вы уверены, что хотите удалить следующий сеанс?</h2>
    <p><strong>Дата начала:</strong> <%= seance.getDateStart() %></p>
    <p><strong>Время начала:</strong> <%= seance.getTimeStart() %></p>
    <form action="/deleteSeance" method="post">
        <input type="hidden" name="id" value="<%= seance.getId() %>">
        <button type="submit" class="button">Удалить</button>
        <a href="/events" class="button cancel">Отмена</a>
    </form>
</div>
</body>
</html>
