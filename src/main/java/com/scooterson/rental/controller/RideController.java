package com.scooterson.rental.controller;

import com.scooterson.rental.model.Ride;
import com.scooterson.rental.service.RideService;
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
@RequestMapping(path = "ride", produces = MediaType.APPLICATION_JSON_VALUE)
public class RideController {

    private final Logger LOGGER = LoggerFactory.getLogger(RideController.class);
    private final RideService service;

    @Autowired
    public RideController(RideService service) {
        this.service = service;
    }

    @GetMapping(value= "/all")
    public ResponseEntity<List<Ride>> loadAll() {
        LOGGER.info("start loadAll users");
        try {
            List<Ride> objects = service.findAll();
            LOGGER.info("Found {} users", objects.size());
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ride> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne Ride by id: ", id);
        try {
            Optional<Ride> user = service.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user.isPresent()?user.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/create")
    public ResponseEntity<Ride> create(@RequestBody Ride ride) {
        LOGGER.info("start creating Ride: ", ride);
        try {
            service.create(ride);
            return new ResponseEntity<>(ride, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ride> update(@PathVariable int id, @RequestBody Ride object) {
        LOGGER.info("start update Ride: ", object);
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
