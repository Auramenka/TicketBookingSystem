package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.model.User;
import com.innowise.ticketbookingsystem.repository.UserRepository;
import com.innowise.ticketbookingsystem.repository.impl.UserRepositoryImpl;
import com.innowise.ticketbookingsystem.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public void registerUser(String username, String email, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId());
        if (existingUser == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
