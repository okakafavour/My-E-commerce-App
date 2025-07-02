package org.example.dto.response;

import lombok.Data;
import org.example.data.model.OrderItems;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private String userId;
    private String productId;
    private List<OrderItems> items;
    private double totalPrice;
    private LocalDateTime createdAt;
    private String deliveryAddress;
}
