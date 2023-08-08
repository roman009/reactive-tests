package org.github.roman009.order.entity;

import lombok.Builder;
import lombok.Data;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.OrderStatus;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class OrderEntity {
    private String id;
    private List<OrderLineItemEntity> lineItems;
    private OrderStatus status;

    public Order toDto() {
        return Order.builder()
                .id(UUID.fromString(this.id))
                .lineItems(this.lineItems.stream().map(OrderLineItemEntity::toDto).toList())
                .status(this.status)
                .build();
    }
}
