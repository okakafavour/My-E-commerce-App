package org.example.controller;

import org.example.data.model.Order;
import org.example.data.repository.CartRepository;
import org.example.dto.request.CartRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.OrderResponse;
import org.example.dto.response.RegisterResponse;
import org.example.enums.Role;
import org.example.service.CustomersServiceImpl;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomersServiceImpl customersService;

    @Autowired
    CartRepository cartRepository;


    @PutMapping("/update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody RegisterRequest request) {
        customersService.updateProfile(request);
        return ResponseEntity.ok("Profile updated successfully");
    }


    @PostMapping("/add-to-cart")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) {
        CartResponse response = customersService.addToCart(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/place-order/{customerId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable String customerId) {
        OrderResponse response = customersService.placeOrder(customerId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/remove-from-cart/{customerId}/{productId}")
    public ResponseEntity<CartResponse> removeFromCart(@PathVariable String customerId, @PathVariable String productId){
        CartResponse response = customersService.removeFromCart(customerId, productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view-cart/{customerId}")
    public ResponseEntity<List<String>> viewCart(@PathVariable String customerId){
        List<String> cartItems = customersService.viewCart(customerId);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/view-orders/{customerId}")
    public ResponseEntity<List<Order>> viewOrders(@PathVariable String customerId){
        List<Order> orders = customersService.viewOrders(customerId);
        return ResponseEntity.ok(orders);
    }

}


