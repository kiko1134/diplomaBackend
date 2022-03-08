package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ServiceRepo extends JpaRepository<Service,Long> {
    Optional<Service> existsByName(String name);
}
