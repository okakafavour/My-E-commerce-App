package org.example.service;

import org.example.data.model.Customer;
import org.example.data.model.User;
import org.example.data.repository.CustomerRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.CustomerLoginRequest;
import org.example.dto.request.CustomerRegisterRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CustomerLoginResponse;
import org.example.dto.response.CustomerRegisterResponse;
import org.example.exception.InvalidCustomerException;
import org.example.exception.UserNotFoundException;
import org.example.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomersServiceImpl implements CustomersService{

    @Autowired
    UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("customer not found"));
    }

    @Override
    public void updateProfile(RegisterRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
    }


    @Override
    public void addToCart(String customerId, String productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new InvalidCustomerException(" customer not found"));

        List<String> cart = customer.getCartId() == null || customer.getCartId().isEmpty()
                ? new ArrayList<>()
                : new ArrayList<>(List.of(customer.getCartId().split(",")));

        cart.add(productId);
        customer.setCartId(String.join(",", cart));
        customerRepository.save(customer);
    }

    @Override
    public void removeFromCart(String customerId, String productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        List<String> cart = customer.getCartId() == null || customer.getCartId().isEmpty()
                ? new ArrayList<>()
                : new ArrayList<>(List.of(customer.getCartId().split(",")));

        cart.remove(productId);
        customer.setCartId(String.join(",", cart));
        customerRepository.save(customer);
    }

    @Override
    public List<String> viewCart(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        boolean emptyCart = customer.getCartId() == null || customer.getCartId().isEmpty();
        if(emptyCart) new ArrayList<>();

        return new ArrayList<>(List.of(customer.getCartId().split(",")));
    }

    @Override
    public void placeOrder(String userId) {
        
    }

    @Override
    public List<String> viewOrders(String userId) {
        return List.of();
    }
}
