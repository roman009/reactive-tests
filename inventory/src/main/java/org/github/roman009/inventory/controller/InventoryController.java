package org.github.roman009.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.Product;
import org.github.roman009.inventory.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return productService.createOrder(order);
    }

    @DeleteMapping
    public Order revertOrder(@RequestBody Order order) {
        return productService.revertOrder(order);
    }

    @PostMapping("/products/")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
