package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import com.innowise.ticketbookingsystem.util.AuthenticationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AuthenticationUtil authUtil = new AuthenticationUtil(userService);

        if (authUtil.authenticateUser(req, username, password)) {
            resp.sendRedirect("/events");
        } else {
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
