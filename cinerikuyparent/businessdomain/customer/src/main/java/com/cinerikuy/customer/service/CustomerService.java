package com.cinerikuy.customer.service;

import com.cinerikuy.customer.entity.Customer;
import com.cinerikuy.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String post(Customer customer) {
        String encodedPassword = this.passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        return customer.getUsername();
    }

    public boolean isNewUsername(String username) {
        if(this.findByUsername(username) == null)
            return true;
        return false;
    }

    public boolean isNewDni(String dni) {
        if(this.findByDni(dni) == null)
            return true;
        return false;
    }

    public Customer findByUsername(String username) {
        Optional<Customer> findByUsername = customerRepository.findByUsername(username);
        if(findByUsername.isPresent())
            return findByUsername.get();
        return null;
    }

    private Customer findByDni(String dni) {
        Optional<Customer> findByDni = customerRepository.findByDni(dni);
        if(findByDni.isPresent())
            return findByDni.get();
        return null;
    }

    public boolean comparePasswords(String password, String dbPassword) {
        return this.passwordEncoder.matches(password, dbPassword);
    }
}
