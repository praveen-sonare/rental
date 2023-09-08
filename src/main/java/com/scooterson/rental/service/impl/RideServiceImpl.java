package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Ride;
import com.scooterson.rental.repository.RideRepository;
import com.scooterson.rental.service.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {
    private final Logger LOGGER = LoggerFactory.getLogger(RideServiceImpl.class);
    private final RideRepository repository;

    @Autowired
    public RideServiceImpl(RideRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ride create(Ride object) {
        return repository.save(object);
    }

    @Override
    public Optional<Ride> find(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Ride> findAll() {
        return repository.findAll();
    }

    @Override
    public Ride update(int id, Ride object) {
        object.setId(id);
        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Ride> object = repository.findById(id);
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
