<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.SeatDto" %>
<%@ page import="com.innowise.ticketbookingsystem.model.Role" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%
    List<SeatDto> seats = (List<SeatDto>) request.getAttribute("seats");
    UserDto user = (UserDto) session.getAttribute("userDto");
    boolean isUser = (user != null && Role.USER == user.getRole());
%>
<html>
<head>
    <title>Список мест</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .occupied {
            background-color: gray;
        }
        input[type="checkbox"] {
            cursor: pointer;
        }
        .button-container {
            margin-top: 20px;
        }
        .button {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<h2>Выбор мест в кинозале</h2>
<form action="/bookSeats" method="post">
    <table border="1">
        <tr>
            <th>Номер ряда</th>
            <th>Номер места</th>
            <th>Занято</th>
        </tr>
        <%
            for (SeatDto seat : seats) {
                String rowClass = seat.getIsOccupied() ? "occupied" : "";
        %>
        <tr class="<%= rowClass %>">
            <td><%= seat.getRowNumber() %></td>
            <td><%= seat.getSeatNumber() %></td>
            <td>
                <input type="checkbox" name="selectedSeats" value="<%= seat.getId() %>" <%= seat.getIsOccupied() ? "disabled" : "" %> />
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <div class="button-container">
        <%
            if (isUser) {
        %>
        <input type="submit" class="button" value="Забронировать" />
        <%
            }
        %>
        <input type="button" class="button" value="Назад" onclick="history.back();" />
    </div>
</form>
</body>
</html>