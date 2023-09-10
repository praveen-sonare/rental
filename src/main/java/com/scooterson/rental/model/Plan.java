package com.scooterson.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "vehicle_type")
    private int vehicleType;
    private int period;

    @Column(name = "need_picture")
    private boolean needPicture;
    private boolean waiver;
    private double price;

}
