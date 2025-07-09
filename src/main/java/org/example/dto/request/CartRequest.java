package org.example.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private String customerId;
    private String productId;
    private int quantity;
}
