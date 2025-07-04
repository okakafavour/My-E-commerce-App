package org.example.dto.request;

import lombok.Data;
import org.example.data.model.Item;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private List<Item> items;
    private double price;
}
