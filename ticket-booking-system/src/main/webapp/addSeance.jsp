<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Seance</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }

    h2 {
      color: #333;
      text-align: center;
    }

    form {
      background: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      max-width: 400px;
      margin: auto;
    }

    label {
      display: block;
      margin-bottom: 8px;
      font-weight: bold;
    }

    input[type="date"],
    input[type="time"],
    input[type="submit"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #28a745;
      color: white;
      border: none;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
      background-color: #218838;
    }

    .form-group {
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<h2>Add New Seance</h2>
<form action="/addSeance" method="post">
  <input type="hidden" id="eventId" name="eventId" value="${param.eventId}">

  <div class="form-group">
    <label for="dateStart">Date Start:</label>
    <input type="date" id="dateStart" name="dateStart" required>
  </div>

  <div class="form-group">
    <label for="timeStart">Time Start:</label>
    <input type="time" id="timeStart" name="timeStart" required>
  </div>

  <input type="submit" value="Add Seance">
</form>
</body>
</html>