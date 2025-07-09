package org.example.controller;

import org.example.data.model.Order;
import org.example.data.repository.CartRepository;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.OrderResponse;
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
    private UserServiceImpl userService;

    @Autowired
    private CustomersServiceImpl customersService;

    @Autowired
    private CartRepository cartRepository;

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody RegisterRequest request) {
        try {
            customersService.updateProfile(request);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating profile: " + e.getMessage());
        }
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest request) {
        try {
            CartResponse response = customersService.addToCart(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding to cart: " + e.getMessage());
        }
    }

    @PostMapping("/place-order/{customerId}")
    public ResponseEntity<?> placeOrder(@PathVariable("customerId") String customerId) {
        try {
            OrderResponse response = customersService.placeOrder(customerId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error placing order: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove-from-cart/{customerId}/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable String customerId, @PathVariable String productId) {
        try {
            CartResponse response = customersService.removeFromCart(customerId, productId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error removing from cart: " + e.getMessage());
        }
    }

    @GetMapping("/view-cart/{customerId}")
    public ResponseEntity<?> viewCart(@PathVariable("customerId") String customerId) {
        try {
            List<CartResponse> cartItems = customersService.viewCart(customerId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error viewing cart: " + e.getMessage());
        }
    }

    @GetMapping("/view-orders/{customerId}")
    public ResponseEntity<?> viewOrders(@PathVariable("customerId") String customerId) {
        try {
            List<Order> orders = customersService.viewOrders(customerId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error viewing orders: " + e.getMessage());
        }
    }
}
