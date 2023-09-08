package com.scooterson.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int vehicle_id;
    private int headlight;
    private String headlight_note;
    private int mirror_light;
    private String mirror_light_note;
    private int mirror;
    private String mirror_note;
    private int turn_lights;
    private String turn_lights_note;
    private int handle_bar;
    private String handle_bar_note;
    private int cable;
    private String cable_note;
    private int screws;
    private String screws_note;
    private int fairings;
    private String fairings_note;
    private int tube;
    private String tube_note;
    private String created_at;
    private String updated_at;
    private String photo_location;
}
