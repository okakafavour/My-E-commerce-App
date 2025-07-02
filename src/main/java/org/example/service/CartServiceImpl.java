package org.example.service;

import org.example.data.model.Cart;
import org.example.data.model.CartItems;
import org.example.data.repository.CartRepository;
import org.example.dto.request.CartRequest;
import org.example.dto.response.CartResponse;
import org.example.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.util.Mapper.*;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartResponse addToCart(CartRequest request) {
        Optional<Cart> OptionalCart = cartRepository.findById(request.getUserId());
        Cart cart = OptionalCart.orElse(new Cart(request.getUserId(), new ArrayList<>(), 0.0));

        CartItems items = mapToCartItems(request);
        addCartItems(cart, items);
        Cart savedCart = cartRepository.save(cart);
        return mapToCartItemsResponse(savedCart);
    }

    @Override
    public CartResponse removeFromCart(String userId , String productId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(" user not found"));
        List<CartItems> items = cart.getItems();
        items.removeIf(item -> item.getProductId().equals(productId));

        Cart updatedCart = cartRepository.save(cart);
        return mapToCartItemsResponse(updatedCart);
    }

    @Override
    public void clearCart(String userId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found: " + userId));

        cart.setItems(new ArrayList<>());
        cartRepository.save(cart);
    }

    private void addCartItems(Cart cart, CartItems newItem){
        if(cart.getItems() == null) cart.setItems(new ArrayList<>());

        List<CartItems> items = cart.getItems();
        boolean itemExists = false;

        for (CartItems item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                itemExists = true;
                break;
            }
        }
        if (!itemExists) items.add(newItem);
    }
}
