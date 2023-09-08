package com.scooterson.rental.service;

import com.scooterson.rental.model.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    //User find(int id);
    Optional<Tenant> find(int id);
    Tenant findByUsername(String username);

    List<Tenant> findAll();

    Tenant create(Tenant object);

    Tenant update(int id, Tenant object);

    boolean delete(int id);

    boolean deleteAll();
}
