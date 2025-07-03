package org.example.util;

import org.example.data.model.Cart;
import org.example.data.model.CartItems;
import org.example.data.model.Customer;
import org.example.data.model.User;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToUser(RegisterRequest request) {
        if (request == null) return null;



        Customer user = new Customer();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setEnable(request.isEnable());
        return user;
    }

    public RegisterResponse mapToRegisterResponse(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Registered Successfully");
        response.setRole(user.getRole());
        return response;
    }

    public static CartItems mapToCartItems(CartRequest request) {
        CartItems items = new CartItems();
        items.setProductName(request.getProductName());
        items.setDescription(request.getDescription());
        items.setPrice(request.getPrice());
        items.setQuantity(request.getQuantity());
        return items;
    }

    public static CartResponse mapToCartItemsResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserId(cart.getUserId());
        cartResponse.setItems(cart.getItems());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }

}
