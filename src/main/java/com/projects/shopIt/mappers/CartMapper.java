package com.projects.shopIt.mappers;

import com.projects.shopIt.dtos.CartDto;
import com.projects.shopIt.dtos.CartItemDto;
import com.projects.shopIt.entities.Cart;
import com.projects.shopIt.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
