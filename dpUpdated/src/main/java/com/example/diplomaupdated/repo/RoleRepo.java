package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String role);
}
