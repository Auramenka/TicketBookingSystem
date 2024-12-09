package com.innowise.ticketbookingsystem.dto;

import com.innowise.ticketbookingsystem.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role = Role.USER;
}
