package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dto.request.CustomerRegisterRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.RegisterResponse;
import org.example.enums.Role;
import org.example.exception.InvalidCustomerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomersServiceImplTest {

    @Autowired
   CustomersService customersService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testForCustomerToUpdateProfile(){
        CustomerRegisterRequest request = new CustomerRegisterRequest();
        request.setFirstName("Thank");
        request.setLastName("grace");
        request.setEmail("grace12@gmail.com");
        request.setPassword("boladada");
        request.setRole(Role.CUSTOMER);
        customersService.register(request);

        RegisterRequest updateRequest = new RegisterRequest();
        updateRequest.setEmail("grace12@gmail.com");
        updateRequest.setPhoneNumber("09137217467");
        updateRequest.setFirstName("Daniel");
        customersService.updateProfile(updateRequest);

        User updatedCustomerProfile = userRepository.findByEmail("grace12@gmail.com")
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        assertEquals("Daniel", updatedCustomerProfile.getFirstName());
    }

}