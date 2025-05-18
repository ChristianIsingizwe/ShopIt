package com.projects.shopIt.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quantity can't be null")
    @Min(value = 1, message = "Quantity can't be less than 1")
    private Integer quantity;
}
