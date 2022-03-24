package com.example.diplomaupdated.service;

import com.example.diplomaupdated.DTO.UserDto;
import com.example.diplomaupdated.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addNewUser(UserDto userDto);
    void deleteUser(Long userId);
    void updateUser(Long userId,String email);
    void addFavoriteService(Long serviceId,Long workshop_id, Long userId);
    User getUserById(Long userId);
}
