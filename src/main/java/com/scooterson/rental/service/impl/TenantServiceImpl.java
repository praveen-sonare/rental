package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Tenant;
import com.scooterson.rental.repository.TenantRepository;
import com.scooterson.rental.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {
    private final Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);
    private final TenantRepository repository;

    @Autowired
    public TenantServiceImpl(TenantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tenant create(Tenant user) {

        return repository.save(user);
    }

    @Override
    public Optional<Tenant> find(int id) {
        return repository.findById(id);
    }

    @Override
    public Tenant findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<Tenant> findAll() {
        return repository.findAll();
    }

    @Override
    public Tenant update(int id, Tenant object) {
        object.setId(id);
        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Tenant> object = repository.findById(id);
            if (object.isPresent())
                repository.delete(object.get());
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
