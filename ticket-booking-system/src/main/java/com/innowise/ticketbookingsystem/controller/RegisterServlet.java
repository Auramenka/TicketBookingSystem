package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        req.setAttribute("username", username);
        req.setAttribute("email", email);
        req.setAttribute("password", password);

        if (userService.isUsernameExists(username)) {
            req.setAttribute("usernameError", "Такое имя пользователя уже существует.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if (userService.isEmailExists(email)) {
            req.setAttribute("emailError", "Такая электронная почта уже зарегистрирована.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        if (password.length() < 6) {
            req.setAttribute("passwordError", "Пароль должен содержать не менее 6 символов");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        userService.registerUser(username, email, password);

        resp.sendRedirect("/registrationSuccess.jsp");
    }
}
