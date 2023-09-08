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
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int rider_id;
    private String uuid;
    private String ride_start_time;
    private String ride_end_time;
    private int start_odo;
    private int end_odo;
    private int vehicle_check_before_ride;
    private int vehicle_check_after_ride;
    private int status;
    private int transaction_id;
    private String txn_amount;
    private String txn_comment;
    private String txn_status;
}
