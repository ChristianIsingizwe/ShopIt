package com.projects.shopIt.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemToCartRequest {
    @NotNull
    private Long productId;
}
