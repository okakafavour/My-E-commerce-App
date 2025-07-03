package org.example.service;

import org.example.data.model.Customer;
import org.example.dto.request.CustomerLoginRequest;
import org.example.dto.request.CustomerRegisterRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CustomerLoginResponse;
import org.example.dto.response.CustomerRegisterResponse;

import java.util.List;

public interface CustomersService {
    Customer findCustomerByEmail(String email);
    void updateProfile(RegisterRequest request);
    void addToCart(String userId, String productId);
    void removeFromCart(String userId, String productId);
    List<String> viewCart(String userId);
    void placeOrder(String userId);
    List<String> viewOrders(String userId);
}
