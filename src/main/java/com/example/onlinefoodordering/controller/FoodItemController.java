package com.example.onlinefoodordering.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.onlinefoodordering.entity.FoodItem;
import com.example.onlinefoodordering.service.FoodItemService;

@RestController
@RequestMapping("/api/food-items")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.addFoodItem(foodItem);
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItem(@PathVariable Long id) {
        return foodItemService.getFoodItem(id);
    }

    @PutMapping("/{id}")
    public FoodItem updateFoodItem(
            @PathVariable Long id,
            @RequestBody FoodItem foodItem) {

        return foodItemService.updateFoodItem(id, foodItem);
    }

    @PatchMapping("/{id}/availability")
    public FoodItem updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        return foodItemService.updateAvailability(id, available);
    }

    @DeleteMapping("/{id}")
    public String deleteFoodItem(@PathVariable Long id) {

        foodItemService.deleteFoodItem(id);

        return "Food item deleted successfully";
    }
}