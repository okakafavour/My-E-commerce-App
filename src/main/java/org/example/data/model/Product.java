package org.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
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
