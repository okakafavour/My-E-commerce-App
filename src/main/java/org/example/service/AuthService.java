//package org.example.service;
//
//import org.example.data.model.User;
//import org.example.dto.request.RegisterRequest;
//import org.example.dto.response.RegisterResponse;
//import org.example.enums.Role;
//
//public class AuthService {
//
//    public RegisterResponse register(RegisterRequest request) {
//        User user = mapper.mapToUser(request);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Automatically assign role
//        if (request.getRole() == null) {
//            user.setRole(Role.CUSTOMER); // default
//        } else {
//            user.setRole(request.getRole()); // could be SELLER, ADMIN etc
//        }
//
//        userRepository.save(user);
//        return mapper.mapToRegisterResponse(user);
//    }
//
//}
