package org.example.service;

import org.example.data.model.Admin;
import org.example.data.model.Customer;
import org.example.data.model.Seller;
import org.example.data.model.User;
import org.example.data.repository.AdminRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.request.AdminRegisterRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.AdminRegisterResponse;
import org.example.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUP(){
        adminRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testToRegisterAdminToTheDataBase(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("Admin");
        request.setLastName("User");
        request.setEmail("admin12@gmail.com");
        request.setPassword("admin123");
        request.setPhoneNumber("08012345678");

        AdminRegisterResponse response = adminService.register(request);

        Admin savedAdmin = adminRepository.findByEmail("admin12@gmail.com");

        assertNotNull(savedAdmin);
        assertNotEquals("admin123", savedAdmin.getPassword());
        assertTrue(savedAdmin.getPassword().startsWith("$2a$"));
        assertEquals("Admin registered successfully", response.getMessage());
    }


    @Test
    public void testToLoginAsAdmin(){

        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("Admin");
        request.setLastName("User");
        request.setEmail("admin12@gmail.com");
        request.setPassword("admin123");
        request.setPhoneNumber("08012345678");
        adminService.register(request);

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setEmail("admin12@gmail.com");
        adminLoginRequest.setPassword("admin123");
        AdminLoginResponse response =  adminService.login(adminLoginRequest);
        assertEquals("Login Successfully", response.getMessage());
    }

    @Test
    public void testThatAdminCanViewTheListOfUser(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("SecondAdmin");
        request.setLastName("jide");
        request.setEmail("jide12@gmail.com");
        request.setPassword("jide123");
        request.setEnable(true);
        request.setPhoneNumber("08112345678");
        AdminRegisterResponse response = adminService.register(request);
        assertEquals("Admin registered successfully", response.getMessage());

        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setEmail("jide12@gmail.com");
        loginRequest.setPassword("jide123");

        AdminLoginResponse loginResponse = adminService.login(loginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());

        Customer user1 = new Customer();
        user1.setEmail("user1@gmail.com");
        user1.setPassword("password1");
        user1.setFirstName("User ");
        user1.setLastName("One");
        user1.setRole(Role.CUSTOMER);
        userRepository.save(user1);

        Customer user2 = new Customer();
        user2.setEmail("user2@gmail.com");
        user2.setPassword("password2");
        user2.setFirstName("User");
        user2.setRole(Role.CUSTOMER);
        user2.setLastName("Two");
        userRepository.save(user2);

        Seller user3 = new Seller();
        user3.setEmail("user13@gmail.com");
        user3.setPassword("password2");
        user3.setFirstName("User");
        user3.setRole(Role.VENDOR);
        user3.setLastName("Three");
        userRepository.save(user3);

        Seller user4 = new Seller();
        user4.setFirstName("User");
        user4.setLastName("Four");
        user4.setEmail("user412@gmail.com");
        user4.setRole(Role.VENDOR);
        userRepository.save(user4);

        List<User> users = adminService.getAllUsers("jide12@gmail.com");
        assertEquals(4, users.size());
    }

    @Test
    public void testThatAdminCanDeleteAUser(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("ThirdAdmin");
        request.setLastName("jide");
        request.setEmail("sam12@gmail.com");
        request.setPassword("sam123");
        request.setEnable(true);
        request.setPhoneNumber("08112345678");
        AdminRegisterResponse response = adminService.register(request);
        assertEquals("Admin registered successfully", response.getMessage());

        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setEmail("sam12@gmail.com");
        loginRequest.setPassword("sam123");

        AdminLoginResponse loginResponse = adminService.login(loginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());

        Customer user1 = new Customer();
        user1.setEmail("user1@gmail.com");
        user1.setPassword("password1");
        user1.setFirstName("User ");
        user1.setLastName("One");
        user1.setRole(Role.CUSTOMER);
        userRepository.save(user1);

        Customer user2 = new Customer();
        user2.setEmail("user2@gmail.com");
        user2.setPassword("password2");
        user2.setFirstName("User");
        user2.setRole(Role.CUSTOMER);
        user2.setLastName("Two");
        userRepository.save(user2);

        adminService.deleteUserByEmail("user2@gmail.com");
        assertEquals(1, userRepository.count());
    }
}