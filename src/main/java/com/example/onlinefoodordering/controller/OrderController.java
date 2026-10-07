package com.example.onlinefoodordering.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.onlinefoodordering.entity.Order;
import com.example.onlinefoodordering.entity.OrderItem;
import com.example.onlinefoodordering.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place Order
    @PostMapping
    public Order placeOrder(
            @RequestParam Long customerId,
            @RequestBody OrderItem item) {

        return orderService.placeOrder(customerId, item);
    }

    // Get Order
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {

        return orderService.getOrder(id);
    }

    // Get Customer Orders
    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(
            @PathVariable Long customerId) {

        return orderService.getCustomerOrders(customerId);
    }

    // Update Order Status
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderService.updateOrderStatus(id, status);
    }

    // Update Payment Status
    @PutMapping("/{id}/payment")
    public Order updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderService.updatePaymentStatus(id, status);
    }
}