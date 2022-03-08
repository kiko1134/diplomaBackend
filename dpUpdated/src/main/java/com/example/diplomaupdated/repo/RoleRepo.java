package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String role);
}
