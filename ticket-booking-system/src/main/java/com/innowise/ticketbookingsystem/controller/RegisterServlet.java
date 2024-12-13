package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.service.UserService;
import com.innowise.ticketbookingsystem.service.impl.UserServiceImpl;
import com.innowise.ticketbookingsystem.util.MapperUtility;
import com.innowise.ticketbookingsystem.util.ValidationUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;

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
        UserDto userDto = MapperUtility.mapToUserDto(req);

        if (ValidationUtility.validateUserDto(req, resp, userDto, validator)
                || ValidationUtility.checkUsernameExists(req, resp, userDto, userService)
                || ValidationUtility.checkEmailExists(req, resp, userDto, userService)) {
            return;
        }

        userService.registerUser(userDto);

        resp.sendRedirect("/registrationSuccess.jsp");
    }
}