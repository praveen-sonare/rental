package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Vehicle;
import com.scooterson.rental.repository.VehicleRepository;
import com.scooterson.rental.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private final VehicleRepository repository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle create(Vehicle object) {

        return repository.save(object);
    }

    @Override
    public Optional<Vehicle> find(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Vehicle> findByuuid(String uuid) {
        return repository.findByuuid(uuid);
    }

    @Override
    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    @Override
    public Vehicle update(String uuid, Vehicle object) {
        Optional<Vehicle> vehicle = findByuuid(uuid);
        if(vehicle.isPresent())
        {
            object.setId(vehicle.get().getId());
            repository.save(object);
        }

        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Vehicle> object = repository.findById(id);
            if (object.isPresent())
                repository.delete(object.get());
            return true;
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String uuid) {
        try {
            Optional<Vehicle> object = repository.findByuuid(uuid);
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
