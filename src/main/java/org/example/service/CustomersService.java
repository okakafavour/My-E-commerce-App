package org.example.service;

import org.example.data.model.Customer;
import org.example.data.model.Order;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.OrderResponse;

import java.util.List;

public interface CustomersService {
    void updateProfile(RegisterRequest request);
    CartResponse addToCart(CartRequest request);
    CartResponse removeFromCart(String userId, String productId);
    List<CartResponse> viewCart(String userId);
    OrderResponse placeOrder(String customerId);
    List<Order> viewOrders(String customerId);

}
