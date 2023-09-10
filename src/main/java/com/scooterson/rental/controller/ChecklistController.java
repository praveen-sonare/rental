package com.scooterson.rental.controller;

import com.scooterson.rental.model.Checklist;
import com.scooterson.rental.service.ChecklistService;
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
@RequestMapping(path = "checklist", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChecklistController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChecklistController.class);
    private final ChecklistService service;

    @Autowired
    public ChecklistController(ChecklistService service) {
        this.service = service;
    }

    @GetMapping(value= "/all")
    public ResponseEntity<List<Checklist>> loadAll() {
        LOGGER.info("start loadAll users");
        try {
            List<Checklist> objects = service.findAll();
            LOGGER.info("Found {} users", objects.size());
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Checklist> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne Ride by id: ", id);
        try {
            Optional<Checklist> user = service.find(id);
            LOGGER.info("Found: {}", user);
            return new ResponseEntity<>(user.isPresent()?user.get(): null, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value= "/create")
    public ResponseEntity<Checklist> create(@RequestBody Checklist checklist) {
        LOGGER.info("start creating Checklist: ", checklist);
        try {
            service.create(checklist);
            return new ResponseEntity<>(checklist, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Checklist> update(@PathVariable int id, @RequestBody Checklist object) {
        LOGGER.info("start update Checklist: ", object);
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
