package com.example.diplomaupdated.service;

import com.example.diplomaupdated.DTO.userDto;
import com.example.diplomaupdated.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addNewUser(userDto userDto);
    void deleteUser(Long userId);
    void updateUser(Long userId, String name, String email, String password);

}
