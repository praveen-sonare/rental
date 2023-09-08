package com.scooterson.rental.repository;

import com.scooterson.rental.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {
    //Checklist findByUsername(String username);
}
