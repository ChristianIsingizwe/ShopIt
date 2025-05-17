package com.projects.shopIt.mappers;

import com.projects.shopIt.dtos.ProductDto;
import com.projects.shopIt.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);
}
