package org.example.util;

import org.example.data.model.*;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.dto.response.RegisterResponse;
import org.example.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CartItems mapToCartItems(CartRequest request, Product product) {
        CartItems cartItems = new CartItems();
        cartItems.setProductName(product.getProductName());

        Item item = new Item();
        item.setId(product.getId());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
        item.setQuantity(request.getQuantity());
        item.setStatus(product.getStatus());

        cartItems.setItemDetails(item);
        return cartItems;
    }

    public static CartResponse mapToCartItemsResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCustomerId(cart.getUserId());

        List<Item> itemList = cart.getItems().stream().map(cartItem -> {
            Item item = new Item();
            item.setId(cartItem.getItemDetails().getId());
            item.setDescription(cartItem.getItemDetails().getDescription());
            item.setPrice(cartItem.getItemDetails().getPrice());
            item.setQuantity(cartItem.getItemDetails().getQuantity());
            item.setStatus(cartItem.getItemDetails().getStatus());
            item.setProductName(cartItem.getProductName());
            return item;
        }).collect(Collectors.toList());

        cartResponse.setItems(itemList);
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }

}
