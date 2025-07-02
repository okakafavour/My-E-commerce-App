package org.example.data.model;

import lombok.Data;

@Data
public class CartItems {
    private String productId;
    private String name;
    private String description;
    private double price;
//    `private int quantity;`
}
