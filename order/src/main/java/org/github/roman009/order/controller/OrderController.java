package org.github.roman009.order.controller;

import lombok.RequiredArgsConstructor;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.OrderStatus;
import org.github.roman009.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order processedOrder = orderService.createOrder(order);
        if (processedOrder.getStatus().equals(OrderStatus.FAILED)) {
            throw new RuntimeException("Order processing failed");
        }
        return processedOrder;
    }
}
