package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.User;

import java.util.List;

public interface UserRepository {

    void save(User user);
    void update(User user);
    void deleteById(Long id);
    List<User> getAllUsers();
    User findUserByUsername(String username);
    boolean findByUsername(String username);
    boolean findByEmail(String email);
    User findById(Long id);
}
