package com.cinerikuy.customer.dto;

import lombok.Data;

@Data
public class CustomerLoginRequest {
    private String username;
    private String password;
}
