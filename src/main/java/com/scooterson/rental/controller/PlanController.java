package com.scooterson.rental.controller;

import com.scooterson.rental.model.Plan;
import com.scooterson.rental.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanController {

    private final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);
    private final PlanService service;

    @Autowired
    public PlanController(PlanService service) {
        this.service = service;
    }

    @RequestMapping(value= "/allCustPlans/{custId}", method = RequestMethod.GET)
    public ResponseEntity<List<Plan>> loadAllCustomerPlan(@PathVariable int custId) {
        LOGGER.info("start load All customer plans");
        try {
            List<Plan> object = service.findBycustomerId(custId);
            LOGGER.info("Found {} users", object.size());
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Plan> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Optional<Plan> user = service.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user.isPresent()?user.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/add")
    public ResponseEntity<Plan> addPlan(@RequestBody Plan object) {
        LOGGER.info("Adding a new plan: ", object);
        try {
            service.addPlan(object);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Plan> update(@PathVariable int id, @RequestBody Plan object) {
        LOGGER.info("updating planr: ", object);
        try {
            service.update(id, object);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (service.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
