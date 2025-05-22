package com.projects.shopIt.services;

import com.projects.shopIt.dtos.CheckoutRequest;
import com.projects.shopIt.dtos.CheckoutResponse;
import com.projects.shopIt.entities.Order;
import com.projects.shopIt.exceptions.CartEmptyException;
import com.projects.shopIt.exceptions.CartNotFoundException;
import com.projects.shopIt.repositories.CartRepository;
import com.projects.shopIt.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());
        orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
