<%@ page import="com.innowise.ticketbookingsystem.util.BookingUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подтверждение заказа</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
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
            padding: 8px;
            box-sizing: border-box;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
        }
        .button-group button {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button-group .buy {
            background-color: #4CAF50;
            color: white;
        }
        .button-group .reserve {
            background-color: #008CBA;
            color: white;
        }
        .button-group .back {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Подтверждение заказа</h2>
    <div class="form-group">
        <label for="name">Название мероприятия:</label>
        <input type="text" name="name" value="<%= BookingUtil.get("eventName") %>">
    </div>
    <div class="form-group">
        <label for="dateStart">Дата сеанса:</label>
        <input type="date" name="dateStart" value="<%= BookingUtil.get("seanceDate") %>">
    </div>
    <div class="form-group">
        <label for="timeStart">Начало сеанса:</label>
        <input type="time" name="timeStart" value="<%= BookingUtil.get("seanceTime") %>">
    </div>
    <div class="form-group">
        <label for="selectedSeats">Выбранные места:</label>
        <input type="text" id="selectedSeats" name="selectedSeats" value="<%= String.join(", ", (String[]) request.getAttribute("seats")) %>" readonly>
    </div>

    <div class="button-group">
        <button class="buy">Купить</button>
        <button class="reserve">Забронировать</button>
        <button class="back" onclick="window.history.back();">Назад</button>
    </div>
</div>
</body>
</html>