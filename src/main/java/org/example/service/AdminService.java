package org.example.service;

import org.example.data.model.Order;
import org.example.data.model.Product;
import org.example.data.model.User;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.request.AdminRegisterRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.AdminRegisterResponse;

import java.util.List;

public interface AdminService {
    AdminRegisterResponse register(AdminRegisterRequest request);
    AdminLoginResponse login(AdminLoginRequest request);
    List<User> getAllUsers(String email);
    void deleteUserByEmail(String email);
    void deleteUserById(String userId);
    List<Product> viewAllProducts();
    void deleteProductById(String productId);
    List<Order> viewAllOrders();
}
