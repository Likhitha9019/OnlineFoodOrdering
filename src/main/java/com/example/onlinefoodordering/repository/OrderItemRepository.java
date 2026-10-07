package com.example.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinefoodordering.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}