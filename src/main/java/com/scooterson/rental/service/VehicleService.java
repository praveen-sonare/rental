package com.scooterson.rental.service;

import com.scooterson.rental.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    //User find(int id);
    Optional<Vehicle> find(int id);
    Vehicle findByuuid(String username);

    List<Vehicle> findAll();

    Vehicle create(Vehicle object);

    Vehicle update(int id, Vehicle object);

    boolean delete(int id);

    boolean deleteAll();
}
