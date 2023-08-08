package org.github.roman009.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Builder
@Jacksonized
@Data
public class Order {
    private UUID id;
    private List<OrderLineItem> lineItems;
    private OrderStatus status;

    public OrderBuilder toBuilder() {
        return Order.builder()
                .id(this.id)
                .lineItems(this.lineItems)
                .status(this.status);
    }
}
