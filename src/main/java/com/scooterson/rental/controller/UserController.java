package com.scooterson.rental.controller;

import com.scooterson.rental.model.User;
import com.scooterson.rental.service.UserService;
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
@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value= "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> loadAll() {
        LOGGER.info("start loadAll users");
        try {
            List<User> users = userService.findAll();
            LOGGER.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Optional<User> user = userService.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user.isPresent()?user.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        LOGGER.info("start creating user: ", user);
        try {
            userService.create(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        LOGGER.info("start update user: ", user);
        try {
            userService.update(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        if (userService.delete(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
