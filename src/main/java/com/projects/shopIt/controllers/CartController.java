package com.projects.shopIt.controllers;

import com.projects.shopIt.dtos.AddItemToCartRequest;
import com.projects.shopIt.dtos.CartDto;
import com.projects.shopIt.dtos.CartItemDto;
import com.projects.shopIt.entities.Cart;
import com.projects.shopIt.mappers.CartMapper;
import com.projects.shopIt.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId, AddItemToCartRequest request) {

    }
}
