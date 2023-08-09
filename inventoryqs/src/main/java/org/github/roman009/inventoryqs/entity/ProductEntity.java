package org.github.roman009.inventoryqs.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.github.roman009.common.dto.Product;

import java.util.UUID;

@Builder
@Data
@MongoEntity(collection = "productEntity")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @BsonId
    private String id;
    private String name;
    private int stock;

    public Product toDto() {
        return Product.builder()
                .id(UUID.fromString(this.id))
                .name(this.name)
                .stock(this.stock)
                .build();
    }
}
