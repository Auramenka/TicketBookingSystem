package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.model.User;

public interface UserService {
    void registerUser(String username, String email, String password);
    void updateUser(User user);
    void deleteUser(Long id);
    UserDto findByUsername(String username);
    UserDto findById(Long id);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
}
