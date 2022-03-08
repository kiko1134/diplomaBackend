package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByAccountId(Long accountId);
}
