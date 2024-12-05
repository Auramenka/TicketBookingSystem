<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add seance</title>
</head>
<body>
<h2>Add New Seance</h2>
<form action="/addSeance" method="post">
  <input type="hidden" id="eventId" name="eventId" value="${param.eventId}">

  <label for="dateStart">Date Start:</label>
  <input type="date" id="dateStart" name="dateStart" required><br><br>

  <label for="timeStart">Time Start:</label>
  <input type="time" id="timeStart" name="timeStart" required><br><br>

  <input type="submit" value="Add Seance">
</form>
</body>
</html>