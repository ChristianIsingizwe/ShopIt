package com.projects.shopIt.dtos;

import java.math.BigDecimal;

public class OrderItemDto {
    private OrderProductDto product;
    private int quantity;
    private BigDecimal totalPrice;
}
