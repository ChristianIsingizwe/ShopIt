package com.projects.shopIt.controllers;

import com.projects.shopIt.dtos.*;
import com.projects.shopIt.exceptions.CartNotFoundException;
import com.projects.shopIt.exceptions.ProductNotFoundException;
import com.projects.shopIt.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId, @RequestBody AddItemToCartRequest request) {

        var cartItemDto = cartService.addToCart(cartId, request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable UUID cartId) {
        var cartDto = cartService.getCart(cartId);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemRequest request)
    {
       var cartItemDto= cartService.updateItem(cartId, productId, request.getQuantity());
       return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId
    )
    {
        cartService.removeItem(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto("Cart not found")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorDto("Product not found")
        );
    }
}
