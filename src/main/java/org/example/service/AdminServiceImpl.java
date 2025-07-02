package org.example.service;

import org.example.data.model.Admin;
import org.example.data.repository.AdminRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.OrderResponse;
import org.example.dto.response.ProductResponse;
import org.example.enums.Role;
import org.example.util.PasswordHashingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;


    public void initAdmin() {
        String email = "admin12@gmail.com";

        if (adminRepository.findByEmail(email) == null) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(PasswordHashingMapper.hashPassword("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setEnable(true);
            admin.setFullName("Admin");

            adminRepository.save(admin);
        }

    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest request) {
        return null;
    }

    @Override
    public List<UserRepository> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUserByEmail(String email) {

    }

    @Override
    public void deleteUserById(String userId) {

    }

    @Override
    public List<ProductResponse> viewAllProducts() {
        return List.of();
    }

    @Override
    public void deleteProductById(String productId) {

    }

    @Override
    public List<OrderResponse> viewAllOrders() {
        return List.of();
    }
}
