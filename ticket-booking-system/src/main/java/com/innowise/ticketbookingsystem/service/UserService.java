package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.model.User;

public interface UserService {
    void registerUser(String username, String email, String password);
    void updateUser(User user);
    void deleteUser(Long id);
    User findByUsername(String username);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
}
