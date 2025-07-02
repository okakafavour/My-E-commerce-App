package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public class AdminController {

        @GetMapping("/users")
        public ResponseEntity<?> getAllUsers() {
            return ResponseEntity.ok("List of all users");
        }

        @DeleteMapping("/delete-user/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable String id) {
            return ResponseEntity.ok("User deleted by admin");
        }
    }


