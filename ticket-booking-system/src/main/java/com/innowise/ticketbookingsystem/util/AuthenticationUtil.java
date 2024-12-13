package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.exceptions.AuthenticationException;
import com.innowise.ticketbookingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

@Slf4j
public class AuthenticationUtil {

    private UserService userService;

    public AuthenticationUtil(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticateUser(HttpServletRequest req, String username, String password) {
        try {
            UserDto userDto = userService.findByUsername(username);

            if (Objects.isNull(userDto)) {
                log.error("Пользователь не найден");
                throw new AuthenticationException("Пользователь не найден");
            }

            BookingUtil.put("userId", userDto.getId());

            if (!BCrypt.checkpw(password, userDto.getPassword())) {
                req.setAttribute("passwordError", "Неверный пароль");
                return false;
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("userDto", userDto);
                return true;
            }
        } catch (AuthenticationException e) {
            req.setAttribute("usernameError", e.getMessage());
            return false;
        }
    }
}
