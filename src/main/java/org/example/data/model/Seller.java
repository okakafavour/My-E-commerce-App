package org.example.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sellers")
public class Seller extends  User {

    private String businessName;
    private List<String> productId;
    private List<String> orderId;
}
