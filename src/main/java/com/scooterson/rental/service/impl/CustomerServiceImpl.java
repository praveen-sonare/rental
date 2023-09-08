package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Customer;
import com.scooterson.rental.repository.CustomerRepository;
import com.scooterson.rental.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer object) {

        return repository.save(object);
    }

    @Override
    public Optional<Customer> find(int id) {
        return repository.findById(id);
    }

    @Override
    public Customer findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer update(int id, Customer object) {
        object.setId(id);
        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Customer> user = repository.findById(id);
            if (user.isPresent())
                repository.delete(user.get());
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            repository.deleteAll();
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
