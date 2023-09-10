package com.scooterson.rental.controller;

import com.scooterson.rental.model.Vehicle;
import com.scooterson.rental.service.VehicleService;
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
@RequestMapping(path = "vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {

    private final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @RequestMapping(value= "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> loadAllCustomerPlan() {
        LOGGER.info("start load All vehicles");
        try {
            List<Vehicle> object = service.findAll();
            LOGGER.info("Found {} vehicles", object.size());
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Vehicle> loadOne(@PathVariable String uuid) {
        LOGGER.info("start loadOne Vehicle by uuid: ", uuid);
        try {
            Optional<Vehicle> object = service.findByuuid(uuid);
            LOGGER.info("Found: {}", object);
            return new ResponseEntity<>(object.isPresent()?object.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/add")
    public ResponseEntity<Vehicle> addPlan(@RequestBody Vehicle object) {
        LOGGER.info("Adding a new Vehicle: ", object);
        try {
            service.create(object);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<Vehicle> update(@PathVariable String uuid, @RequestBody Vehicle object) {
        LOGGER.info("updating Vehicle: ", object);
        try {
            service.update(uuid, object);
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

    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String uuid) {
        if (service.delete(uuid))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
