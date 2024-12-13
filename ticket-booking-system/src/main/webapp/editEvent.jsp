<%@ page import="com.innowise.ticketbookingsystem.dto.EventDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EventDto eventDto = (EventDto) request.getAttribute("eventDto");
%>
<html>
<head>
    <title>Редактирование мероприятия</title>
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
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="date"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"], .back-button {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
        }
        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
        .back-button {
            background-color: #007bff;
            color: white;
            margin-right: 10px;
        }
        .back-button:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
<h2>Редактирование мероприятия</h2>
<form action="editEvent" method="post">
    <input type="hidden" name="id" value="<%= eventDto.getId() %>"/>

    <label for="name">Название:</label>
    <input type="text" name="name" value="<%= eventDto.getName() %>" required/>

    <label for="description">Описание:</label>
    <textarea name="description" required><%= eventDto.getDescription() %></textarea>

    <label for="dateStart">Дата начала:</label>
    <input type="date" name="dateStart" value="<%= eventDto.getDateStart() %>" required/>

    <label for="dateEnd">Дата окончания:</label>
    <input type="date" name="dateEnd" value="<%= eventDto.getDateEnd() %>" required/>

    <label for="category">Категория:</label>
    <input type="text" name="category" value="<%= eventDto.getCategory() %>" required/>

    <label for="photo">Ссылка на фото:</label>
    <input type="text" name="photo" value="<%= eventDto.getPhoto() %>" required/>

    <input type="submit" value="Обновить мероприятие"/>
    <button type="button" class="back-button" onclick="window.history.back();">Назад</button>
</form>
</body>
</html>