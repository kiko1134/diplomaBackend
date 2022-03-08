package com.example.diplomaupdated.service;

import com.example.diplomaupdated.DTO.UserDto;
import com.example.diplomaupdated.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addNewUser(UserDto userDto);
    void deleteUser(Long userId);
    void updateUser(Long userId, String name, String email, String password);
    void addFavoriteService(Long serviceId, Long user_id);

}
