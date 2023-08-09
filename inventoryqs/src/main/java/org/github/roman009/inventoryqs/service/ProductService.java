package org.github.roman009.inventoryqs.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.roman009.common.dto.Product;
import org.github.roman009.inventoryqs.entity.ProductEntity;
import org.github.roman009.inventoryqs.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public Uni<List<Product>> getAllProducts() {
        return Uni.createFrom().item(productRepository.listAll().stream().map(ProductEntity::toDto).toList());
    }

    //
//    @Transactional
//    public Order createOrder(Order order) {
//        order.getLineItems().forEach(lineItem -> {
//            var product = productRepository.findById(lineItem.getProductId().toString()).orElseThrow();
//            product.setStock(product.getStock() - lineItem.getQuantity());
//            productRepository.save(product);
//        });
//
//        return order.toBuilder().status(OrderStatus.SUCCESS).build();
//    }
//
//    @Transactional
//    public Order revertOrder(Order order) {
//        order.getLineItems().forEach(lineItem -> {
//            var product = productRepository.findById(lineItem.getProductId().toString()).orElseThrow();
//            product.setStock(product.getStock() + lineItem.getQuantity());
//            productRepository.save(product);
//        });
//
//        return order.toBuilder().status(OrderStatus.REVERTED).build();
//    }
//
//    @Transactional
//    public Product addProduct(Product product) {
//        var productEntity = ProductEntity.builder().stock(product.getStock()).name(product.getName()).id(UUID.randomUUID().toString()).build();
//        productRepository.save(productEntity);
//        return productEntity.toDto();
//    }
}
