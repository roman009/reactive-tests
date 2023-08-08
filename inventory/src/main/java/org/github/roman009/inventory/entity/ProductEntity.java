package org.github.roman009.inventory.entity;

import lombok.Builder;
import lombok.Data;
import org.github.roman009.common.dto.Product;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Data
public class ProductEntity {
    @Id
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
