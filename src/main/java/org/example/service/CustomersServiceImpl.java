package org.example.service;

import org.example.data.model.*;
import org.example.data.repository.CartRepository;
import org.example.data.repository.CustomerRepository;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.OrderResponse;
import org.example.exception.InvalidCustomerException;
import org.example.exception.UserNotFoundException;
import org.example.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomersServiceImpl implements CustomersService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public void updateProfile(RegisterRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
    }


    public CartResponse addToCart(CartRequest request) {
        CartItems item = Mapper.mapToCartItems(request);

        Cart cart = new Cart();
        cart.setUserId(request.getUserId());
        cart.setItems(List.of(item));
        cart.setTotalPrice(item.getItemDetails().getPrice() * item.getItemDetails().getQuantity());
        cartRepository.save(cart);
        return Mapper.mapToCartItemsResponse(cart);
    }


    @Override
    public CartResponse removeFromCart(String customerId, String productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidCustomerException("Customer not found"));

        List<Item> items = customer.getItems();
        if (items == null) {
            items = new ArrayList<>();
        }

        items.removeIf(item -> item.getId().equals(productId));

        customer.setItems(items);
        customerRepository.save(customer);

        double totalPrice = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        CartResponse response = new CartResponse();
        response.setItems(items);
        response.setTotalPrice(totalPrice);

        return response;
    }

    @Override
    public List<String> viewCart(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        boolean emptyCart = customer.getCartId() == null || customer.getCartId().isEmpty();
        if(emptyCart) return new ArrayList<>();

        return new ArrayList<>(List.of(customer.getCartId().split(",")));
    }

    @Override
    public OrderResponse placeOrder(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        List<Item> cartItems = customer.getCartItems();
        if(cartItems.isEmpty()) throw new IllegalArgumentException("cart is empty");

        double totalPrice = cartItems.stream()
                .mapToDouble(Item::getTotalPrice)
                .sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setItems(cartItems);
        order.setTotalAmount(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        customer.setCartItems(new ArrayList<>());
        customerRepository.save(customer);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setTotalPrice(totalPrice);
        response.setItemCount(cartItems.size());
        response.setOrderDate(order.getOrderDate());
        return response;
    }


    @Override
    public List<Order> viewOrders(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidCustomerException("customer not found"));

        return orderRepository.findByCustomerId(customerId);
    }

}
