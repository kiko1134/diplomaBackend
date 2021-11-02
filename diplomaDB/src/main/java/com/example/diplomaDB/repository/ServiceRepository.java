package com.example.diplomaDB.repository;

import com.example.diplomaDB.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
