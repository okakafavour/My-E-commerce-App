package org.example.service;

import org.example.data.model.Admin;
import org.example.data.model.Order;
import org.example.data.model.Product;
import org.example.data.model.User;
import org.example.data.repository.AdminRepository;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.ProductRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.request.AdminRegisterRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.AdminRegisterResponse;
import org.example.dto.response.LoginResponse;
import org.example.enums.Role;
import org.example.exception.InvalidPasswordException;
import org.example.exception.InvalidProductException;
import org.example.exception.UnauthorizedException;
import org.example.exception.UserNotFoundException;
import org.example.util.PasswordHashingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;



    @Override
    public AdminRegisterResponse register(AdminRegisterRequest request) {
        Admin admin = new Admin();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setPassword(PasswordHashingMapper.hashPassword(request.getPassword()));
        admin.setRole(Role.ADMIN);
        admin.setPhoneNumber(request.getPhoneNumber());
        adminRepository.save(admin);

        AdminRegisterResponse response = new AdminRegisterResponse();
        response.setMessage("Admin registered successfully");
        return response;
    }


    @Override
    public AdminLoginResponse login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail());

        boolean passwordMatch = PasswordHashingMapper.checkPassword(request.getPassword(), admin.getPassword());
        if(!passwordMatch) throw new InvalidPasswordException("Invalid password");

        AdminLoginResponse loginResponse = new AdminLoginResponse();
        loginResponse.setMessage("Login Successfully");
        return loginResponse;
    }

    @Override
    public List<User> getAllUsers(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if(admin == null || admin.getRole() != Role.ADMIN)  throw new UnauthorizedException("You are not authorize to view users");

        return userRepository.findAll();
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User with the email" + email + "is not found"));
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("user with the id" + userId + "is not found"));
        userRepository.delete(user);
    }

    @Override
    public List<Product> viewAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new InvalidProductException("Product not found"));
        productRepository.deleteById(productId);
    }

    @Override
    public List<Order> viewAllOrders() {
        return orderRepository.findAll();
    }
}