<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EventDto eventDto = (EventDto) request.getAttribute("eventDto");
%>
<html>
<head>
    <title>Изменить мероприятие</title>
</head>
<body>
<h2>Edit Event</h2>
<form action="editEvent" method="post">
    <input type="hidden" name="id" value="<%= eventDto.getId() %>"/>

    <label for="name">Name:</label>
    <input type="text" name="name" value="<%= eventDto.getName() %>" required/><br/>

    <label for="description">Description:</label>
    <textarea name="description" required><%= eventDto.getDescription() %></textarea><br/>

    <label for="dateStart">Start Date:</label>
    <input type="date" name="dateStart" value="<%= eventDto.getDateStart() %>" required/><br/>

    <label for="dateEnd">End Date:</label>
    <input type="date" name="dateEnd" value="<%= eventDto.getDateEnd() %>" required/><br/>

    <label for="category">Category:</label>
    <input type="text" name="category" value="<%= eventDto.getCategory() %>" required/><br/>

    <label for="photo">Photo:</label>
    <input type="text" name="photo" value="<%= eventDto.getPhoto() %>" required/><br/>

    <input type="submit" value="Update Event"/>
</form>
</body>
</html>
