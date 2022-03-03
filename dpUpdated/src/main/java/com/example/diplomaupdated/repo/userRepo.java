package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface userRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByName(String name);
}
