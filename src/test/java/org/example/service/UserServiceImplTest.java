package org.example.service;

import org.example.Main;
import org.example.enums.Role;
import org.example.data.repository.UserRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = Main.class)
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testToRegister(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Ugo");
        registerRequest.setLastName("daniel");
        registerRequest.setEmail("sam12@gmail.com");
        registerRequest.setPassword("1234");
        registerRequest.setRole(Role.CUSTOMER);

        userService.register(registerRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterAndLogin(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Musa");
        registerRequest.setLastName("Ijidola");
        registerRequest.setEmail("kim24@gmail.com");
        registerRequest.setPassword("12345");
        registerRequest.setRole(Role.CUSTOMER);
        userService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kim24@gmail.com");
        loginRequest.setPassword("12345");

        LoginResponse response = userService.login(loginRequest);
        assertEquals("Login Successfully", response.getMessage());
    }
  
}