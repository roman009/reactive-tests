package org.github.roman009.inventoryqs.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.github.roman009.inventoryqs.entity.ProductEntity;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepositoryBase<ProductEntity, String> {

}
