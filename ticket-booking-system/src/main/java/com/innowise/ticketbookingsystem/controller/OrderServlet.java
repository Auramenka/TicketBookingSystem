package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.OrderService;
import com.innowise.ticketbookingsystem.service.impl.OrderServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
import com.innowise.ticketbookingsystem.util.MapperUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("userDto");

        if (Objects.nonNull(userDto) && Objects.nonNull(userDto.getId())) {
            List<OrderDto> orders = orderService.getOrdersByUserId(userDto.getId());
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

        OrderDto orderDto = MapperUtility.mapToOrderDto(req, action, userId);
        if (Objects.isNull(orderDto)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            return;
        }

        orderService.createOrder(orderDto);
        req.getRequestDispatcher("orderConfirmation.jsp").forward(req, resp);

        BookingUtil.clear();
    }
}
