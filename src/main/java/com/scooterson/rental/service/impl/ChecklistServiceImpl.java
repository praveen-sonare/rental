package com.scooterson.rental.service.impl;

import com.scooterson.rental.model.Checklist;
import com.scooterson.rental.repository.ChecklistRepository;
import com.scooterson.rental.service.ChecklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistServiceImpl implements ChecklistService {
    private final Logger LOGGER = LoggerFactory.getLogger(ChecklistServiceImpl.class);
    private final ChecklistRepository repository;

    @Autowired
    public ChecklistServiceImpl(ChecklistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Checklist create(Checklist object) {
        return repository.save(object);
    }

    @Override
    public Optional<Checklist> find(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Checklist> findAll() {
        return repository.findAll();
    }

    @Override
    public Checklist update(int id, Checklist object) {
        object.setId(id);
        return repository.save(object);
    }

    @Override
    public boolean delete(int id) {
        try {
            Optional<Checklist> object = repository.findById(id);
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
