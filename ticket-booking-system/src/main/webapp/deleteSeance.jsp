<%@ page import="com.innowise.ticketbookingsystem.model.Seance" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Seance seance = (Seance) request.getAttribute("seance");
%>
<html>
<head>
    <title>Удаление сеанса</title>
</head>
<body>
<h2>Вы уверены, что хотите удалить следующий сеанс?</h2>
<p><strong>Дата начала:</strong><%= seance.getDateStart()%>></p>
<p><strong>Время начала:</strong><%= seance.getTimeStart()%></p>
<form action="/deleteSeance" method="post">
  <input type="hidden" name="id" value="<%= seance.getId()%>">
  <button type="submit">Удалить</button>
  <a href="/events">Отмена</a>
</form>
</body>
</html>
