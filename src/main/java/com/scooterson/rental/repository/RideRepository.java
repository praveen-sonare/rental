package com.scooterson.rental.repository;

import com.scooterson.rental.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
    //Ride findByUsername(String uuid);
}
