package org.example.util;

import org.example.data.model.Customer;
import org.example.data.model.User;
import org.example.dto.request.CustomerRegisterRequest;
import org.example.dto.response.CustomerRegisterResponse;
import org.example.enums.Role;
import org.springframework.stereotype.Service;

import static org.example.enums.Role.CUSTOMER;

@Service
public class CustomerMapper {


    public static Customer toUserEntity(CustomerRegisterRequest request) {
        Customer user = new Customer();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.CUSTOMER);
        user.setEnable(true);
        return user;
    }

    public static Customer toCustomerEntity(CustomerRegisterRequest request, String userId) {
        Customer customer = new Customer();
        customer.setId(userId);
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setShippingAddress(request.getAddress());
        return customer;
    }

    public static CustomerRegisterResponse toResponse(Customer customer, String email) {
        CustomerRegisterResponse response = new CustomerRegisterResponse();
        response.setCustomerId(customer.getId());
        response.setEmail(email);
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setAddress(customer.getShippingAddress());
        return response;
    }
}
