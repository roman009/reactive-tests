package org.github.roman009.inventory.repository;

import org.github.roman009.inventory.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
