package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByName(String name);
    Optional<Account> findAccountByEmail(String email);
}
