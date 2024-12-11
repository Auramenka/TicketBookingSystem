package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
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

        try {
            UserDto userDto = userService.findByUsername(username);

            BookingUtil.put("userId", userDto.getId());

            if (!BCrypt.checkpw(password, userDto.getPassword())) {
                req.setAttribute("passwordError", "Неверный пароль");
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("userDto", userDto);
                resp.sendRedirect("/events");
                return;
            }
        } catch (RuntimeException e) {
            req.setAttribute("usernameError", e.getMessage());
        }

        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
