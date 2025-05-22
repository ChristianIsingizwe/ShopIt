package com.projects.shopIt.controllers;

import com.projects.shopIt.dtos.CheckoutRequest;
import com.projects.shopIt.dtos.CheckoutResponse;
import com.projects.shopIt.dtos.ErrorDto;
import com.projects.shopIt.exceptions.CartEmptyException;
import com.projects.shopIt.exceptions.CartNotFoundException;
import com.projects.shopIt.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
