package kris.diploma.diplomaBackend.repository;

import kris.diploma.diplomaBackend.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
