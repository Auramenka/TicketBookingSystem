<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.SeatDto" %>
<%
    List<SeatDto> seats = (List<SeatDto>) request.getAttribute("seats");
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
            background-color: black;
            color: white;
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
<h2>Выберите место(а)</h2>
<form action="seats" method="get">
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
        <input type="submit" class="button" value="Забронировать" />
        <input type="button" class="button" value="Купить" />
    </div>
</form>
</body>
</html>