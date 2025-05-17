package com.projects.shopIt.controllers;

import com.projects.shopIt.dtos.ProductDto;
import com.projects.shopIt.entities.Product;
import com.projects.shopIt.mappers.ProductMapper;
import com.projects.shopIt.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts(
         @RequestParam(name = "categoryId", required = false) Byte categoryId
    ) {

        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

       return   products
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
       var product = productRepository.findById(id).orElse(null);
       if (product == null) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(productMapper.toDto(product));
    }

}
