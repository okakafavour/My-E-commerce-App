package org.example.dto.response;

import lombok.Data;
import org.example.data.model.CartItems;
import org.example.data.model.Item;

import java.util.List;

@Data
public class CartResponse {
    private String customerId;
    private List<Item> items;
    private double totalPrice;
}
