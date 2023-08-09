package org.github.roman009.inventory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.OrderStatus;
import org.github.roman009.common.dto.Product;
import org.github.roman009.inventory.entity.ProductEntity;
import org.github.roman009.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productEntity -> Product.builder().id(UUID.fromString(productEntity.getId())).stock(productEntity.getStock()).name(productEntity.getName()).build())
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional
    public Order createOrder(Order order) {
        order.getLineItems().forEach(lineItem -> {
            var product = productRepository.findById(lineItem.getProductId().toString()).orElseThrow();
            product.setStock(product.getStock() - lineItem.getQuantity());
            productRepository.save(product);
        });

        return order.toBuilder().status(OrderStatus.SUCCESS).build();
    }

    @Transactional
    public Order revertOrder(Order order) {
        order.getLineItems().forEach(lineItem -> {
            var product = productRepository.findById(lineItem.getProductId().toString()).orElseThrow();
            product.setStock(product.getStock() + lineItem.getQuantity());
            productRepository.save(product);
        });

        return order.toBuilder().status(OrderStatus.REVERTED).build();
    }

    @Transactional
    public Product addProduct(Product product) {
        var productEntity = ProductEntity.builder().stock(product.getStock()).name(product.getName()).id(UUID.randomUUID().toString()).build();
        productRepository.save(productEntity);
        return productEntity.toDto();
    }
}
