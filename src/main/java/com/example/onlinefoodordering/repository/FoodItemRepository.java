package com.example.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinefoodordering.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
