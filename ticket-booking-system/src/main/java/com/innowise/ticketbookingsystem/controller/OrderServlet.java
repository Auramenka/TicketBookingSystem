package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.model.OrderStatus;
import com.innowise.ticketbookingsystem.service.OrderService;
import com.innowise.ticketbookingsystem.service.impl.OrderServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/bookingOrder")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("userDto");
        Long userId = userDto.getId();

        if (userId != null) {
            List<OrderDto> orders = orderService.getOrdersByUserId(userId);
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("myOrders.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        UserDto userDto = (UserDto) req.getSession().getAttribute("userDto");
        Long userId = userDto.getId();
        Long eventId = (Long) BookingUtil.get("eventId");
        Long bookingId = (Long) BookingUtil.get("bookingId");

        OrderStatus status;
        if ("buy".equals(action)) {
            status = OrderStatus.PURCHASE;
        } else if ("reserve".equals(action)) {
            status = OrderStatus.BOOKING;
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            return;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(status);
        orderDto.setUserId(userId);
        orderDto.setEventId(eventId);
        orderDto.setBookingId(bookingId);

        orderService.createOrder(orderDto);
        req.getRequestDispatcher("orderConfirmation.jsp").forward(req, resp);
    }
}
