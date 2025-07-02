//package org.example.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.dto.request.LoginRequest;
//import org.example.dto.request.RegisterRequest;
//import org.example.dto.response.LoginResponse;
//import org.example.dto.response.RegisterResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//    @RestController
//    @RequestMapping("/api/auth")
//    @RequiredArgsConstructor
//    public class AuthController {
//
//        private final AuthService authService;
//
//        @PostMapping("/register")
//        public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
//            return ResponseEntity.ok(authService.register(request));
//        }
//
//        @PostMapping("/login")
//        public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//            return ResponseEntity.ok(authService.login(request));
//        }
//    }
//
//
