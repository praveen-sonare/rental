package com.scooterson.rental.service;

import com.scooterson.rental.model.Ride;

import java.util.List;
import java.util.Optional;

public interface RideService {
    //User find(int id);
    Optional<Ride> find(int id);

    List<Ride> findAll();

    Ride create(Ride object);

    Ride update(int id, Ride object);

    boolean delete(int id);

    boolean deleteAll();
}
