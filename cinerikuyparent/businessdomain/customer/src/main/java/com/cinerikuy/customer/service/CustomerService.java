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
//        String encodedPassword = this.passwordEncoder.encode(customer.getPassword());
//        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        return customer.getUsername();
    }

    public boolean isNewUsername(String username) {
        if(this.getCustomerByUsername(username) == null)
            return true;
        return false;
    }

    public boolean isNewDni(String dni) {
        if(this.getCustomerByDni(dni) == null)
            return true;
        return false;
    }

    private Customer getCustomerByUsername(String username) {
        Optional<Customer> findByUsername = customerRepository.findByUsername(username);
        if(findByUsername.isPresent())
            return findByUsername.get();
        return null;
    }

    private Customer getCustomerByDni(String dni) {
        Optional<Customer> findByDni = customerRepository.findByDni(dni);
        if(findByDni.isPresent())
            return findByDni.get();
        return null;
    }

    public Customer findByUsername(String username) {
        Optional<Customer> findByUsername = customerRepository.findByUsername(username);
        if(!findByUsername.isPresent()) return null;
        if(!findByUsername.get().isEnabled()) return null;
        return findByUsername.get();
    }

    public boolean comparePasswords(String password, String dbPassword) {
        return this.passwordEncoder.matches(password, dbPassword);
    }
}
