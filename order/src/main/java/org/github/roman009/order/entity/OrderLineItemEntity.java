package org.github.roman009.order.entity;

import lombok.Builder;
import lombok.Data;
import org.github.roman009.common.dto.OrderLineItem;

import java.util.UUID;

@Builder
@Data
public class OrderLineItemEntity {
    private String productId;
    private int quantity;

    public OrderLineItem toDto() {
        return OrderLineItem.builder()
                .productId(UUID.fromString(this.productId))
                .quantity(this.quantity)
                .build();
    }
}
