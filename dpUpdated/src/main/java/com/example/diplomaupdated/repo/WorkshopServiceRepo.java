package com.example.diplomaupdated.repo;

import com.example.diplomaupdated.model.WorkshopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkshopServiceRepo extends JpaRepository<WorkshopService,Long> {
}
