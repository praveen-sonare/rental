package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Plan;
import com.scooterson.rental.repository.PlanRepository;
import com.scooterson.rental.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    private final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);
    private final PlanRepository repository;

    @Autowired
    public PlanServiceImpl(PlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Plan addPlan(Plan plan) {
        List<Plan> plans = findBycustomerId(plan.getCustomerId());
        for (Plan existingPlan: plans){
            if(existingPlan.getCustomerId() == plan.getCustomerId() &&
               existingPlan.getVehicleType() == plan.getVehicleType() &&
               existingPlan.getPeriod() == plan.getPeriod())
            {
                plan.setId(existingPlan.getId());
                return repository.save(plan);
            }
        }

        return repository.save(plan);
    }

    @Override
    public Optional<Plan> find(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Plan> findBycustomerId(int customerId) {
        return repository.findBycustomerId(customerId);
    }

    @Override
    public List<Plan> findAll() {
        return repository.findAll();
    }

    @Override
    public Plan update(int id, Plan object) {
        object.setId(id);
        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Plan> object = repository.findById(id);
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
