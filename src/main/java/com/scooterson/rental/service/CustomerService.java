package com.scooterson.rental.service;

import com.scooterson.rental.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    //User find(int id);
    Optional<Customer> find(int id);
    Customer findByUsername(String username);

    List<Customer> findAll();

    Customer create(Customer object);

    Customer update(int id, Customer object);

    boolean delete(int id);

    boolean deleteAll();
}
