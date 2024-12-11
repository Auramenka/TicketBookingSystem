package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Set;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final Validator validator;

    public RegisterServlet() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword(password);

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDto> violation : violations) {
                req.setAttribute(violation.getPropertyPath().toString() + "Error", violation.getMessage());
            }
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

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

        userService.registerUser(userDto);

        resp.sendRedirect("/registrationSuccess.jsp");
    }
}