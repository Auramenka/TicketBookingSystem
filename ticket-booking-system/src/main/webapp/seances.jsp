<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.SeanceDto" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Сеансы</title>
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
        .seance-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 20px;
        }
        .seance {
            border: 1px solid #ddd;
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            width: calc(50% - 20px);
            box-sizing: border-box;
            display: flex;
            align-items: flex-start;
            flex-direction: column;
        }
        .seance .seance-date {
            display: block;
            margin-bottom: 5px;
        }
        .header-title-container {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 20px 0;
        }
    </style>
</head>
<body>

<h1 style="text-align: center;">Список сеансов</h1>

<div class="seance-container">
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <p style="color: red;"><%= errorMessage %></p>
    <%
    } else {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<SeanceDto> seances = (List<SeanceDto>) request.getAttribute("seances");
        if (seances != null && !seances.isEmpty()) {
            for (SeanceDto seance : seances) {
                String dateStartFormatted = seance.getDateStart() != null ? seance.getDateStart().format(dateFormatter) : "Нет даты начала";
                String timeStartFormatted = seance.getTimeStart() != null ? seance.getTimeStart().format(timeFormatter) : "Нет времени начала";
    %>
    <div class="seance">
        <span class="seance-date"><strong>Дата: <%= dateStartFormatted %>, Начало сеанса: <a href="/seats"><%= timeStartFormatted %></a></strong></span>
    </div>
    <%
        }
    } else {
    %>
    <p>Нет сеансов для отображения</p>
    <%
            }
        }
    %>
</div>

<div style="text-align: center; margin-bottom: 20px;">
    <a href="/events" class="login-button">Перейти на главную страницу</a>
</div>

</body>
</html>
