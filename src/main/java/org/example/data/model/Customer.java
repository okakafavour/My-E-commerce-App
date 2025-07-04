package org.example.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "customers")
public class Customer extends User{
    private List<String> orderId;
    private List<String> orderHistory;
    private List<Item> cartItems;
    private List<Item> items;
    private String cartId;
    private String shippingAddress;

}
