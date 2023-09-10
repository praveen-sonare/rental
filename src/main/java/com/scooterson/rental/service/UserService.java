package com.scooterson.rental.service;

import com.scooterson.rental.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //User find(int id);
    Optional<User> find(int id);
    Optional<User> findByUsername(String username);

    List<User> findAll();

    User create(User object);

    User update(int id, User object);

    boolean delete(int id);

    boolean deleteAll();
}
