package com.example.onlinefoodordering.exception;

public class FoodItemNotFoundException extends RuntimeException {

    public FoodItemNotFoundException(String message) {
        super(message);
    }
}