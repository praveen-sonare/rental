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
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int customerId;
    private int vehicleType;
    private int period;

    private boolean needPicture;
    private boolean waiver;
    private double price;

}
