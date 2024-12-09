<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EventDto eventDto = (EventDto) request.getAttribute("eventDto");
%>
<html>
<head>
    <title>Удаление мероприятия</title>
</head>
<body>
<h2>Вы уверены, что хотите удалить следующее мероприятие?</h2>
<p><strong>Название:</strong><%= eventDto.getName()%>></p>
<p><strong>Описание:</strong><%= eventDto.getDescription()%></p>
<form action="/deleteEvent" method="post">
    <input type="hidden" name="id" value="<%= eventDto.getId()%>">
    <button type="submit">Удалить</button>
    <a href="/events">Отмена</a>
</form>
</body>
</html>
