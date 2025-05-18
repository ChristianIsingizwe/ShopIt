package com.projects.shopIt.dtos;

import java.math.BigDecimal;

public class CartItemDto {
    private CartProductDto product;
    private int quantity;
    private BigDecimal totalPrice;
}
