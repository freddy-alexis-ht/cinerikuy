package com.cinerikuy.customer.controller;

import com.cinerikuy.customer.dto.CustomerRequest;
import com.cinerikuy.customer.dto.CustomerRequestMapper;
import com.cinerikuy.customer.dto.CustomerResponse;
import com.cinerikuy.customer.dto.CustomerResponseMapper;
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

    @PostMapping
    public ResponseEntity<CustomerResponse> signIn(@RequestBody CustomerRequest request) throws BusinessRuleException {
        // Validates username and dni are not repeated
        // Assumes other fields were validated in frontend
        if(!customerService.isNewUsername(request.getUsername()))
            throw new BusinessRuleException("001", "El username ya existe.", HttpStatus.PRECONDITION_FAILED);
        if(!customerService.isNewDni(request.getDni()))
            throw new BusinessRuleException("002", "El DNI ya existe.", HttpStatus.PRECONDITION_FAILED);

        // Maps CustomerRequest to Customer
        Customer customer = cusReqMapper.CustomerRequestToCustomer(request);
        // Encodes password and inserts Customer in DB
        customerService.post(customer);
        // Maps Customer to CustomerResponse
        CustomerResponse response = cusResMapper.CustomerToCustomerResponse(customer);
        // Returns response
        return ResponseEntity.ok(response);
    }

}
