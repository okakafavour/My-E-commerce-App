package org.example.data.model;

import lombok.Data;
import org.example.enums.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "order")
public class Order {
    private String id;
    private String customerId;
    private List<OrderItems> items;
    private double totalPrice;
    private OrderStatus status;
    private String deliveryAddress;
    private LocalDateTime createdAt = LocalDateTime.now();
}
