package kris.diploma.diplomaDB.repository;

import kris.diploma.diplomaDB.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkshopRepository extends JpaRepository<Workshop,Long> {

    @Query("select s from Workshop s where s.email = ?1")
    Optional<Workshop> findWorkshopByEmail(String email);
}
