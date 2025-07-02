package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
@PreAuthorize("hasRole('SELLER')")
    public class SellerController {

        @PostMapping("/add-product")
        public ResponseEntity<?> addProduct() {
            return ResponseEntity.ok("Product added by seller");
        }

        @GetMapping("/orders")
        public ResponseEntity<?> viewOrders() {
            return ResponseEntity.ok("Orders for this seller");
        }
    }


