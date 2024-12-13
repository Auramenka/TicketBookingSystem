package com.innowise.ticketbookingsystem.dto;

import com.innowise.ticketbookingsystem.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;

    @NotBlank(message = "Электронная почта не может быть пустой")
    @Email(message = "Некорректный адрес электронной почты")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым.")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
}
