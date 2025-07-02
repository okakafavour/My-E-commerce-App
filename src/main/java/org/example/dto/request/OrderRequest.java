package org.example.dto.request;

import lombok.Data;
import org.example.data.model.OrderItems;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private List<OrderItems> items;
    private double price;
}
