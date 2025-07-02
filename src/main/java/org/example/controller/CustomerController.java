package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

        @GetMapping("/profile")
        public ResponseEntity<?> viewProfile() {
            return ResponseEntity.ok("Customer profile details...");
        }

        @PostMapping("/place-order")
        public ResponseEntity<?> placeOrder() {
            return ResponseEntity.ok("Order placed!");
        }
    }


