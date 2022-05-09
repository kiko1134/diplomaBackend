package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkshopRepo extends JpaRepository<Workshop,Long> {

}
