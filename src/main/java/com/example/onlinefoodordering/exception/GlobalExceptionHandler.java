package com.example.onlinefoodordering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFound(CustomerNotFoundException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler(FoodItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFoodItemNotFound(FoodItemNotFoundException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler(FoodNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFoodNotAvailable(FoodNotAvailableException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrderNotFound(OrderNotFoundException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidOrderStatus(InvalidOrderStatusException ex) {

        return ex.getMessage();
    }
}