package com.cinerikuy.customer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String cellphone;
    private String role;
    private boolean enabled;
    private boolean hasVoted;
}
