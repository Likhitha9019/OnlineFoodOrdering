package com.example.onlinefoodordering.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.onlinefoodordering.entity.Customer;
import com.example.onlinefoodordering.entity.FoodItem;
import com.example.onlinefoodordering.entity.Order;
import com.example.onlinefoodordering.entity.OrderItem;
import com.example.onlinefoodordering.entity.OrderStatus;
import com.example.onlinefoodordering.entity.PaymentStatus;
import com.example.onlinefoodordering.exception.CustomerNotFoundException;
import com.example.onlinefoodordering.exception.FoodItemNotFoundException;
import com.example.onlinefoodordering.exception.FoodNotAvailableException;
import com.example.onlinefoodordering.exception.InvalidOrderStatusException;
import com.example.onlinefoodordering.exception.OrderNotFoundException;
import com.example.onlinefoodordering.repository.CustomerRepository;
import com.example.onlinefoodordering.repository.FoodItemRepository;
import com.example.onlinefoodordering.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final FoodItemRepository foodItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        FoodItemRepository foodItemRepository) {

        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.foodItemRepository = foodItemRepository;
    }

    // Place Order
    public Order placeOrder(Long customerId, OrderItem item) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        Long foodId = item.getFoodItem().getFoodId();

        FoodItem foodItem = foodItemRepository.findById(foodId)
                .orElseThrow(() ->
                        new FoodItemNotFoundException("Food item not found"));

        if (!foodItem.isAvailable()) {

            throw new FoodNotAvailableException(
                    "Food item is not available: " + foodItem.getName());
        }

        item.setFoodItem(foodItem);

        double total = foodItem.getPrice() * item.getQuantity();

        item.setPrice(foodItem.getPrice());

        Order order = new Order();

        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPaymentStatus(PaymentStatus.PENDING);

        item.setOrder(order);

        order.setOrderItems(List.of(item));

        return orderRepository.save(order);
    }

    // Get Order
    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));
    }

    // Get Customer Orders
    public List<Order> getCustomerOrders(Long customerId) {

        customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        return orderRepository.findByCustomerCustomerId(customerId);
    }

    // Update Order Status
    public Order updateOrderStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));

        try {

            OrderStatus newStatus =
                    OrderStatus.valueOf(status.toUpperCase());

            order.setOrderStatus(newStatus);

            return orderRepository.save(order);

        } catch (IllegalArgumentException e) {

            throw new InvalidOrderStatusException(
                    "Invalid order status");
        }
    }

    // Update Payment Status
    public Order updatePaymentStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));

        try {

            PaymentStatus newStatus =
                    PaymentStatus.valueOf(status.toUpperCase());

            order.setPaymentStatus(newStatus);

            return orderRepository.save(order);

        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                    "Invalid payment status");
        }
    }
}