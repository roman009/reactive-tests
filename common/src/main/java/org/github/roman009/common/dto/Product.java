package org.github.roman009.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Jacksonized
@Data
public class Product {
    private UUID id;
    private String name;
    private int stock;
}
