package com.example.diplomaDB.repository;

import com.example.diplomaDB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select s from User s where s.email = ?1")
    Optional<User>findUserByEmail(String email);
}
