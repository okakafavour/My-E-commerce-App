package org.example.controller;

import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.RegisterResponse;
import org.example.enums.Role;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest request) {
        request.setRole(Role.CUSTOMER);
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/profile")
        public ResponseEntity<?> viewProfile() {
            return ResponseEntity.ok("Customer profile details...");
        }

        @PostMapping("/place-order")
        public ResponseEntity<?> placeOrder() {
            return ResponseEntity.ok("Order placed!");
        }
    }


