package org.example.service;

import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
