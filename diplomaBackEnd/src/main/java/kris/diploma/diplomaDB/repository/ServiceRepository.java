package kris.diploma.diplomaDB.repository;

import kris.diploma.diplomaDB.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
