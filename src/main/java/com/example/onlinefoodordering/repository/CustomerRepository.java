package com.example.onlinefoodordering.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinefoodordering.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}