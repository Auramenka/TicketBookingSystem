<%@ page import="java.util.List" %>
<%@ page import="com.innowise.ticketbookingsystem.dto.OrderDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<OrderDto> orders = (List<OrderDto>) request.getAttribute("orders");
%>
<html>
<head>
    <title>Мои заказы</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Название мероприятия</th>
        <th>Дата начала</th>
        <th>Время начала</th>
        <th>Номер ряда и места</th>
        <th>Статус</th>
    </tr>
    <%
        if (orders != null && !orders.isEmpty()) {
            for (OrderDto order : orders) {
    %>
    <tr>
        <td><%= order.getEventName() %></td>
        <td><%= order.getEventDate() %></td>
        <td><%= order.getEventTime() %></td>
        <td><%= order.getSeatInfo() %></td>
        <td><%= order.getOrderStatus() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Нет доступных заказов</td>
    </tr>
    <%
        }
    %>
</table>

    <br/>

    <a href="/events" style="padding: 10px; background-color: blue; color: white; text-decoration: none; border-radius: 5px;">На главную</a>

</body>
</html>
</body>
</html>