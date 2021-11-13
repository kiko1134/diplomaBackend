package com.example.diplomaDB.service;

import com.example.diplomaDB.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void addNewUser(User user);

    void deleteUser(Long user_id);

    void updateUser(Long userId, String name, String email, String password);
}
