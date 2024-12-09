package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.model.User;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editProfile")
public class EditUserProfile extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        UserDto userDto = userService.findById(id);

        req.setAttribute("userDto", userDto);
        req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        UserDto userDto = userService.findById(id);

        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setEmail(email);

        userService.updateUser(userDto);

        resp.sendRedirect("/events");
    }
}
