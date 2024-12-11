<%@ page import="com.innowise.ticketbookingsystem.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UserDto> users = (List<UserDto>) request.getAttribute("users");
%>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Имя пользователя</th>
        <th>Электронная почта</th>
        <th>Роль</th>
        <th>Действия</th>
    </tr>
    <%
        if (users != null && !users.isEmpty()) {
            for (UserDto user : users) {
    %>
    <tr>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getRole() %></td>
        <td>
            <form action="/deleteUser" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= user.getId() %>"/>
                <a href="/deleteUser?id=<%= user.getId() %>" class="tab">Удалить</a>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Нет пользователей</td>
    </tr>
    <%
        }
    %>
</table>

<br/>

<a href="/events" style="padding: 10px; background-color: blue; color: white; text-decoration: none; border-radius: 5px;">На главную</a>

</body>
</html>