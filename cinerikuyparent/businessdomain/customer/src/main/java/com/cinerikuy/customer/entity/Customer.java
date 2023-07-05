package com.cinerikuy.customer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cr_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
//    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String cellphone;
//    private String role;
    private boolean enabled;
}
