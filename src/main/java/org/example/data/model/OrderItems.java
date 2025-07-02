package org.example.data.model;

import lombok.Data;

@Data
public class OrderItems extends Item {
    private String orderId;
    private boolean shipped;
    private String deliveryStatus;
}