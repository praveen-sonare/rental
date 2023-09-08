package com.scooterson.rental.controller;

import com.scooterson.rental.model.Customer;
import com.scooterson.rental.service.CustomerService;
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
@RequestMapping(path = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService userService) {
        this.service = userService;
    }

    @RequestMapping(value= "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> loadAll() {
        LOGGER.info("start loadAll users");
        try {
            List<Customer> users = service.findAll();
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Optional<Customer> user = service.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user.isPresent()?user.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/create")
    public ResponseEntity<Customer> create(@RequestBody Customer object) {
        LOGGER.info("start creating user: ", object);
        try {
            service.create(object);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> update(@PathVariable int id, @RequestBody Customer object) {
        LOGGER.info("start update user: ", object);
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
