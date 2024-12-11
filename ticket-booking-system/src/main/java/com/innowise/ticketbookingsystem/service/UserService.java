package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.UserDto;

import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
    UserDto findByUsername(String username);
    UserDto findById(Long id);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
}
