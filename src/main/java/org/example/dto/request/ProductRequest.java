package org.example.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String productId;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String category;
    private String imageUrl;

}
