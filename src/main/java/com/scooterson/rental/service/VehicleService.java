package com.scooterson.rental.service;

import com.scooterson.rental.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    //User find(int id);
    Optional<Vehicle> find(int id);
    Optional<Vehicle> findByuuid(String uuid);

    List<Vehicle> findAll();

    Vehicle create(Vehicle object);

    Vehicle update(String uuid, Vehicle object);

    boolean delete(int id);
    boolean delete(String uuid);

    boolean deleteAll();
}
