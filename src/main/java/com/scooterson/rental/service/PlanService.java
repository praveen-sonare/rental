package com.scooterson.rental.service;

import com.scooterson.rental.model.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    //User find(int id);
    Optional<Plan> find(int id);
    List<Plan> findBycustomerId(int customerId);

    List<Plan> findAll();

    Plan addPlan(Plan object);

    Plan update(int id, Plan object);

    boolean delete(int id);

    boolean deleteAll();
}
