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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int owner;
    private String uuid;
    private String public_key;
    private String vehicle_name;
    private String iot_device_id;
    private String iot_device_type;
    private String qa_details_id;
    private String vehicle_type;
    private String vehicle_model;
    private String vehicle_color;
    private int manufac_state_id;
    private int manufac_state_user_id;
    private int battery_cycle;
    private String battery_serial_number;
    private String battery_firmware_version;
    private String controller_serial_number;
    private String controller_software_version;
    private String motor_serial;
    private String particle_id;
    private String particle_firmware_version;
    private int odo;
    private int height;
    private int length;
    private int width;
    private int weight;
    private boolean power;
    private boolean locked;
    private boolean is_available;
    private boolean status;
    private String created_at;
    private String updated_at;
}
