package org.example.data.model;

import lombok.Data;

@Data
public class CartItems extends Item{
    private Item itemDetails;
}
