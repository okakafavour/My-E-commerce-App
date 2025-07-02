package org.example.dto.response;

import lombok.Data;
import org.example.data.model.CartItems;

import java.util.List;

@Data
public class CartResponse {
    private String userId;
    private List<CartItems> items;
    private double totalPrice;
}
