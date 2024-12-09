package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.mappers.UserMapper;
import com.innowise.ticketbookingsystem.model.User;
import com.innowise.ticketbookingsystem.repository.UserRepository;
import com.innowise.ticketbookingsystem.repository.impl.UserRepositoryImpl;
import com.innowise.ticketbookingsystem.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public void registerUser(UserDto userDto) {
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        userDto.setPassword(hashedPassword);
        User user = UserMapper.toEntity(userDto);
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto) {
        User userFromDb = userRepository.findById(userDto.getId());
        if (userFromDb != null) {
            userFromDb.setId(userDto.getId());
            userFromDb.setUsername(userDto.getUsername());
            userFromDb.setEmail(userDto.getEmail());
            userRepository.update(userFromDb);
        } else {
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Пользователь с именем '" + username + "' не найден");
        }
        return UserMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        return UserMapper.toDto(userRepository.findById(id));
    }

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
