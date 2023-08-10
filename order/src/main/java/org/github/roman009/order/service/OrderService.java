package org.github.roman009.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.OrderLineItem;
import org.github.roman009.common.dto.OrderStatus;
import org.github.roman009.order.entity.OrderEntity;
import org.github.roman009.order.entity.OrderLineItemEntity;
import org.github.roman009.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    @Value("${order.inventory-service.url}")
    private String inventoryServiceUrl;

    public List<Order> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntity -> Order.builder()
                        .id(UUID.fromString(orderEntity.getId()))
                        .lineItems(mapLineItemsEntityToDto(orderEntity.getLineItems()))
                        .status(orderEntity.getStatus())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    private List<OrderLineItem> mapLineItemsEntityToDto(List<OrderLineItemEntity> lineItems) {
        return lineItems.stream()
                .map(lineItemEntity -> OrderLineItem.builder()
                        .productId(UUID.fromString(lineItemEntity.getProductId()))
                        .quantity(lineItemEntity.getQuantity())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional
    public Order createOrder(Order order) {
        OrderEntity savedOrder = orderRepository.save(OrderEntity.builder()
                .id(UUID.randomUUID().toString())
                .lineItems(mapLineItemsDtoToEntity(order.getLineItems()))
                .status(OrderStatus.NEW)
                .build());

        RestTemplate restTemplate = new RestTemplate();
        try {
            var res = restTemplate.postForObject(inventoryServiceUrl, savedOrder.toDto(), Order.class);
            assert res != null;
            if (res.getStatus().equals(OrderStatus.SUCCESS)) {
                savedOrder.setStatus(OrderStatus.SUCCESS);
                orderRepository.save(savedOrder);
            } else {
                savedOrder.setStatus(OrderStatus.FAILED);
                orderRepository.save(savedOrder);
                restTemplate.delete(inventoryServiceUrl, savedOrder.toDto());
            }
        } catch (Exception e) {
            log.error("Error while sending order to inventory service", e);
            savedOrder.setStatus(OrderStatus.FAILED);
            orderRepository.save(savedOrder);
        }

        return savedOrder.toDto();
    }

    private List<OrderLineItemEntity> mapLineItemsDtoToEntity(List<OrderLineItem> lineItems) {
        return lineItems.stream()
                .map(lineItem -> OrderLineItemEntity.builder()
                        .productId(lineItem.getProductId().toString())
                        .quantity(lineItem.getQuantity())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }
}
