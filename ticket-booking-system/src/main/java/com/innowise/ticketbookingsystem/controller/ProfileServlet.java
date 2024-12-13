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
import java.util.Objects;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        UserDto user;

        if (Objects.nonNull(userIdParam)) {
            user = userService.findById(Long.valueOf(userIdParam));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing");
            return;
        }

        req.setAttribute("userDto", user);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
