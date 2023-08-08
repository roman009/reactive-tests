package org.github.roman009.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@Jacksonized
@Data
public class OrderLineItem {
    private UUID productId;
    private int quantity;
}
