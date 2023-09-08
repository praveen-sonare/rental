package com.scooterson.rental.service;

import com.scooterson.rental.model.Checklist;

import java.util.List;
import java.util.Optional;

public interface ChecklistService {
    //User find(int id);
    Optional<Checklist> find(int id);

    List<Checklist> findAll();

    Checklist create(Checklist object);

    Checklist update(int id, Checklist object);

    boolean delete(int id);

    boolean deleteAll();
}
