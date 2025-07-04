package org.example.service;

import org.example.data.model.Customer;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.OrderResponse;

import java.util.List;

public interface CustomersService {
    Customer findCustomerByEmail(String email);
    void updateProfile(RegisterRequest request);
    CartResponse addToCart(CartRequest request);
    CartResponse removeFromCart(String userId, String productId);
    List<String> viewCart(String userId);
    OrderResponse placeOrder(String customerId);
    List<String> viewOrders(String userId);

}
