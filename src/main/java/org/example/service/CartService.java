package org.example.service;

import org.example.dto.request.CartRequest;
import org.example.dto.response.CartResponse;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartResponse addToCart(CartRequest request);
    CartResponse removeFromCart(String userId, String productId);
    void clearCart(String userId);
}
