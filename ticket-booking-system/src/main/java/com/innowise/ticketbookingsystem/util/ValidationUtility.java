package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.Set;

public class ValidationUtility {

    public static boolean validateUserDto(HttpServletRequest req, HttpServletResponse resp, UserDto userDto, Validator validator) throws ServletException, IOException, ServletException, IOException {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDto> violation : violations) {
                req.setAttribute(violation.getPropertyPath().toString() + "Error", violation.getMessage());
            }
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    public static boolean checkUsernameExists(HttpServletRequest req, HttpServletResponse resp, UserDto userDto, UserService userService) throws ServletException, IOException {
        if (userService.isUsernameExists(userDto.getUsername())) {
            req.setAttribute("usernameError", "Такое имя пользователя уже существует.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    public static boolean checkEmailExists(HttpServletRequest req, HttpServletResponse resp, UserDto userDto, UserService userService) throws ServletException, IOException {
        if (userService.isEmailExists(userDto.getEmail())) {
            req.setAttribute("emailError", "Такая электронная почта уже зарегистрирована.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return true;
        }
        return false;
    }
}
