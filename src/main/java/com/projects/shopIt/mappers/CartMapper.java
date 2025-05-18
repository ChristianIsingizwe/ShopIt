package com.projects.shopIt.mappers;

import com.projects.shopIt.dtos.CartDto;
import com.projects.shopIt.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
