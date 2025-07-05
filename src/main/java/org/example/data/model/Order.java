package org.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Status;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    private String id;
    private String customerId;
    private List<Item> items;
    private double totalAmount;
    private Status status;
    private boolean shipped;
    private String deliveryStatus;
    private String deliveryAddress;
    private LocalDateTime orderDate = LocalDateTime.now();


}
