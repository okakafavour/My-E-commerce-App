package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dto.request.RegisterRequest;
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
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testForCustomerToUpdateProfile(){
        RegisterRequest CustomerRequest = new RegisterRequest();
        CustomerRequest.setFirstName("Thank");
        CustomerRequest.setLastName("grace");
        CustomerRequest.setEmail("grace12@gmail.com");
        CustomerRequest.setPhoneNumber("09123456789");
        CustomerRequest.setPassword("boladada");
        CustomerRequest.setRole(Role.CUSTOMER);
        CustomerRequest.setEnable(true);
        userService.register(CustomerRequest);

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