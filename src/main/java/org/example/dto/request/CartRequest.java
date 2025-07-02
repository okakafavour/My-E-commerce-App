package org.example.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private String userId;
    private String productId;
    private String productName;
    private String description;
    private int quantity;
    private double price;
}
