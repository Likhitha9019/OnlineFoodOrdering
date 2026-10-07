package com.example.onlinefoodordering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.onlinefoodordering.entity.FoodItem;
import com.example.onlinefoodordering.repository.FoodItemRepository;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItem(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {

        FoodItem existingFood = foodItemRepository.findById(id).orElse(null);

        if (existingFood != null) {
            existingFood.setName(foodItem.getName());
            existingFood.setDescription(foodItem.getDescription());
            existingFood.setPrice(foodItem.getPrice());
            existingFood.setAvailable(foodItem.isAvailable());

            return foodItemRepository.save(existingFood);
        }

        return null;
    }

    public FoodItem updateAvailability(Long id, boolean available) {

        FoodItem foodItem = foodItemRepository.findById(id).orElse(null);

        if (foodItem != null) {
            foodItem.setAvailable(available);
            return foodItemRepository.save(foodItem);
        }

        return null;
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}