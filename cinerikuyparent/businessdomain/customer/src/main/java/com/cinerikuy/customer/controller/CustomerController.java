package com.cinerikuy.customer.controller;

import com.cinerikuy.customer.dto.*;
import com.cinerikuy.customer.entity.Customer;
import com.cinerikuy.customer.exception.BusinessRuleException;
import com.cinerikuy.customer.repository.CustomerRepository;
import com.cinerikuy.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRequestMapper cusReqMapper;
    @Autowired
    private CustomerResponseMapper cusResMapper;

    @PostMapping("/signin")
    public ResponseEntity<CustomerResponse> signIn(@RequestBody CustomerSignInRequest request) throws BusinessRuleException {
        // Validates username and dni are not repeated
        // Assumes other fields were validated in frontend
        if(!customerService.isNewUsername(request.getUsername()))
            throw new BusinessRuleException("001", "El username ya existe.", HttpStatus.PRECONDITION_FAILED);
        if(!customerService.isNewDni(request.getDni()))
            throw new BusinessRuleException("002", "El DNI ya existe.", HttpStatus.PRECONDITION_FAILED);

        // Maps CustomerSignInRequest to Customer
        Customer customer = cusReqMapper.CustomerSignInRequestToCustomer(request);
        // Encodes password and inserts Customer in DB
        String username = customerService.post(customer);
        // Maps Customer to CustomerResponse
        CustomerResponse response = null;
        if(username.equals(customer.getUsername()))
            response = cusResMapper.CustomerToCustomerResponse(customer);
        // Returns response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponse> login(@RequestBody CustomerLoginRequest request) throws BusinessRuleException {
        // Front validates values are not empty and that they are valid
        // Validates username exists in DB
        Customer customer = customerService.findByUsername(request.getUsername());
        if(customer == null)
            throw new BusinessRuleException("003", "Credenciales incorrectas.", HttpStatus.PRECONDITION_FAILED);
        // Validates passwords are the same
        boolean areEqual = customerService.comparePasswords(request.getPassword(), customer.getPassword());
        if(!areEqual)
            throw new BusinessRuleException("004", "Credenciales incorrectas.", HttpStatus.PRECONDITION_FAILED);
        CustomerResponse response = new CustomerResponse();
        response.setUsername(request.getUsername());
        return ResponseEntity.ok(response);
    }

}
