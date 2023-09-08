package com.scooterson.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private @NotNull String username;
    private @NotNull String email;
    private String profile_pic;
    private String address_street;
    private String country;
    private String zip;
    private String phone_number;
    private int country_code;
    private String phone_type;
    private String created_at;
    private String updated_at;
    private String is_active;

    private String first_name;
    private String last_name;
    private String dob;
    private String D_U_N_S;
    private String tax_id;
    private String entity_id;
}
