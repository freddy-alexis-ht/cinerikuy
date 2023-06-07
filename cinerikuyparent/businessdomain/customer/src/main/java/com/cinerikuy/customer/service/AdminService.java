package com.cinerikuy.customer.service;

import com.cinerikuy.customer.entity.Customer;
import com.cinerikuy.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    public CustomerRepository customerRepository;

    public List<Customer> customerFindAll() {
        List<Customer> list = customerRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .collect(Collectors.toList());
    }

    public Customer customerFindById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()) return null;
        return customer.get();
    }

    public Customer customerSaveUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

}
