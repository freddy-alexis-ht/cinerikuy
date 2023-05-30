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
    //@Column(columnDefinition = "varchar(8) default 'customer'")
    private String role;
    //@Column(columnDefinition = "varchar(1) default '1'")
    private String state;
    //@Column(columnDefinition = "boolean default false") // Will store 'f' ('t' for true)
    private boolean hasVoted;
}
