package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.RegisterResponse;
import org.example.exception.InvalidPasswordException;
import org.example.exception.InvalidUserException;
import org.example.exception.UserNotFoundException;
import org.example.util.JwtUtil;
import org.example.util.Mapper;
import org.example.util.PasswordHashingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.example.validation.validations.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
     Mapper mapper;

    @Autowired
    JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest request) {
        String validPhoneNumber = validatePhoneNumber(request.getPhoneNumber());
        String validFirstName = validateName(request.getFirstName());
        String validEmail = validateEmail(request.getEmail());
        String validLastName = validateName(request.getLastName());

        request.setFirstName(validFirstName);
        request.setPhoneNumber(validPhoneNumber);
        request.setEmail(validEmail);
        request.setLastName(validLastName);

        Optional<User> exitingUser = userRepository.findByEmail(request.getEmail());
        if (exitingUser.isPresent()) throw new InvalidUserException("User already exists");

        User user = mapper.mapToUser(request);
        user.setRole(request.getRole());
        String hashedPassword = PasswordHashingMapper.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return mapper.mapToRegisterResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean passwordMatch = PasswordHashingMapper.checkPassword(request.getPassword(), user.getPassword());
        if (!passwordMatch) throw new InvalidPasswordException("Invalid password");

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse response = new LoginResponse();
        response.setMessage("Login Successfully");
        response.setToken(token);
        return response;
    }
}
