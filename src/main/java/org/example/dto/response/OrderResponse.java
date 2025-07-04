package org.example.dto.response;

import lombok.Data;
import org.example.data.model.Item;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private String userId;
    private String productId;
    private String orderId;
    private List<Item> items;
    private double totalPrice;
    private int itemCount;
    private LocalDateTime orderDate;
    private String deliveryAddress;

}
