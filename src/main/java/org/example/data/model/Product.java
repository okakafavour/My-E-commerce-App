package org.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String productName;
    private String category;
    private int quantity;
    private String description;
    private double price;
    private String imageUrl;
    private String sellerId;
    private boolean available;

}
