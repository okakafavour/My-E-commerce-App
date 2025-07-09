package org.example.service;

import org.example.data.model.*;
import org.example.data.repository.*;
import org.example.dto.request.CartRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.OrderResponse;
import org.example.dto.response.RegisterResponse;
import org.example.enums.Status;
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

    @Autowired
    ProductRepository productRepository;


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
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Item item = new Item();
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
        item.setQuantity(request.getQuantity());
        item.setStatus(Status.AVAILABLE);

        CartItems cartItem = new CartItems();
        cartItem.setProductName(product.getProductName());
        cartItem.setItemDetails(item);

        Cart cart = new Cart();
        cart.setUserId(request.getCustomerId());
        cart.setItems(List.of(cartItem));
        cart.setTotalPrice(product.getPrice() * request.getQuantity());

        Cart savedCart = cartRepository.save(cart);

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new InvalidCustomerException("Customer not found"));

        String existingCartIds = customer.getCartId();
        if (existingCartIds == null || existingCartIds.isEmpty()) {
            customer.setCartId(savedCart.getId());
        } else {
            customer.setCartId(existingCartIds + "," + savedCart.getId());
        }

        customerRepository.save(customer);

        return Mapper.mapToCartItemsResponse(savedCart);
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
    public List<CartResponse> viewCart(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidCustomerException("Customer not found"));

        if (customer.getCartId() == null || customer.getCartId().isEmpty()) {
            return new ArrayList<>();
        }

        List<CartResponse> cartResponses = new ArrayList<>();

        String[] cartIds = customer.getCartId().split(",");
        for (String cartId : cartIds) {
            cartRepository.findById(cartId).ifPresent(cart -> {
                CartResponse response = Mapper.mapToCartItemsResponse(cart);
                cartResponses.add(response);
            });
        }

        return cartResponses;
    }


    @Override
    public OrderResponse placeOrder(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidCustomerException("Customer not found"));

        String cartIdString = customer.getCartId();
        if (cartIdString == null || cartIdString.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        List<String> cartIds = List.of(cartIdString.split(","));
        List<Cart> carts = cartRepository.findAllById(cartIds);

        List<Item> allItems = new ArrayList<>();
        for (Cart cart : carts) {
            for (CartItems cartItem : cart.getItems()) {
                allItems.add(cartItem.getItemDetails());
            }
        }

        if (allItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        double totalPrice = allItems.stream()
                .mapToDouble(Item::getTotalPrice)
                .sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setItems(allItems);
        order.setTotalAmount(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        customer.setCartId(null);
        customerRepository.save(customer);

        cartRepository.deleteAll(carts);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setTotalPrice(totalPrice);
        response.setItemCount(allItems.size());
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
