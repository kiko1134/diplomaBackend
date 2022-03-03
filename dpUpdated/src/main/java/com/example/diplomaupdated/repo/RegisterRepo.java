package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepo extends JpaRepository<Register,Long> {

    Optional<Register> findRegisteredByName(String name);
}
