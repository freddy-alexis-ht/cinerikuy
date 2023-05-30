package com.cinerikuy.customer.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String cellphone;
}
