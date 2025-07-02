package org.example.service;

import org.example.data.repository.UserRepository;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.OrderResponse;
import org.example.dto.response.ProductResponse;

import java.util.List;

public interface AdminService {
    AdminLoginResponse login(AdminLoginRequest request);
    List<UserRepository> getAllUsers();
    void deleteUserByEmail(String email);
    void deleteUserById(String userId);
    List<ProductResponse> viewAllProducts();
    void deleteProductById(String productId);
    List<OrderResponse> viewAllOrders();
}
