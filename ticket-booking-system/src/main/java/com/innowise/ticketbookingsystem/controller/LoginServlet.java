package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDto user = userService.findByUsername(username);

        if (user == null) {
            req.setAttribute("usernameError", "Пользователь с таким именем не существует");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            req.setAttribute("passwordError", "Неверный пароль");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/events");
    }
}
