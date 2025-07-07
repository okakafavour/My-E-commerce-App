package org.example.util;

import org.example.data.model.Seller;
import org.example.data.model.User;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class SellerMapper {

    public User mapToSeller(RegisterRequest request) {
        if (request == null) return null;

        Seller user = new Seller();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setEnable(request.isEnable());
        return user;
    }

    public RegisterResponse mapToRegisterResponse(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Registered Successfully");
        response.setRole(user.getRole());
        return response;
    }
}
