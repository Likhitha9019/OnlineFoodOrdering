package com.example.onlinefoodordering.service;

import org.springframework.stereotype.Service;

import com.example.onlinefoodordering.entity.Customer;
import com.example.onlinefoodordering.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}