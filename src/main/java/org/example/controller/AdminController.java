package org.example.controller;

import org.example.data.model.User;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.example.service.AdminServiceImpl;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public class AdminController {


    @Autowired
    UserServiceImpl userService;

    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<?> getAllUsers(@PathVariable("email") String email) {
        List<User> admin = adminService.getAllUsers(email);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        adminService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}




