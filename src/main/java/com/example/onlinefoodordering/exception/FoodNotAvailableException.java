package com.example.onlinefoodordering.exception;

public class FoodNotAvailableException extends RuntimeException {

    public FoodNotAvailableException(String message) {
        super(message);
    }
}