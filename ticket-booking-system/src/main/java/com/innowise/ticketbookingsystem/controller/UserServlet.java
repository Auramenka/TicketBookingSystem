package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");

        if (Objects.nonNull(userIdParam)) {
            List<UserDto> users = userService.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        }
    }
}
