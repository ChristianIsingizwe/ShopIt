package com.projects.shopIt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
