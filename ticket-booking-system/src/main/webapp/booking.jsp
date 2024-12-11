<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.model.Role" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.SeatDto" %>
<%
  UserDto user = (UserDto) session.getAttribute("userDto");
%>
<html>
<head>
  <title>Выбор мест</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      color: #333;
      margin: 0;
      padding: 20px;
    }
    h1 {
      text-align: center;
      color: #2c3e50;
    }
    form {
      max-width: 600px;
      margin: 0 auto;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    .seat-option {
      margin: 10px 0;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background: #f9f9f9;
      display: flex;
      align-items: center;
    }
    .seat-option input {
      margin-right: 10px;
    }
    input[type="submit"], .back-button {
      background-color: #3498db;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
      width: 48%;
      transition: background-color 0.3s ease;
      margin-top: 10px;
    }
    input[type="submit"]:hover, .back-button:hover {
      background-color: #2980b9;
    }
    .back-button {
      background-color: #e74c3c;
    }
    .back-button:hover {
      background-color: #c0392b;
    }
    .button-container {
      display: flex;
      justify-content: space-between;
    }
  </style>
</head>
<body>
<h1>Выберите места</h1>
<form action="bookSeats" method="post">
  <input type="hidden" name="seanceId" value="<%= request.getAttribute("seanceId") %>" />

  <%
    List<SeatDto> availableSeats = (List<SeatDto>) request.getAttribute("availableSeats");
    if (availableSeats != null) {
      for (SeatDto seatDto : availableSeats) {
  %>
  <div class="seat-option">
    <input type="checkbox" name="seats" value="<%= seatDto.getId() %>" />
    Ряд <%= seatDto.getRowNumber() %>, Место <%= seatDto.getSeatNumber() %>
  </div>
  <%
      }
    }
  %>

  <%
    if (user != null && Role.ADMIN != user.getRole()) {
  %>
  <div class="button-container">
    <input type="submit" value="Выбрать" />
  </div>
  <%
    }
  %>
  <div class="button-container">
    <input type="button" class="back-button" value="Назад" onclick="history.back();" />
  </div>
</form>

</body>
</html>