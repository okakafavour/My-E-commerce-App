package org.example.data.model;

import lombok.Data;

@Data
public class CartItems extends Item{
    private String cartId;
    private String userId;
}
