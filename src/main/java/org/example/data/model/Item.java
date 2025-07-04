package org.example.data.model;

import lombok.Data;
import org.example.enums.Status;
import org.springframework.data.annotation.Transient;


@Data
public class Item {
    private String Id;
    private String description;
    private double price;
    private int quantity;
    private Status status;
    private String productName;

    @Transient
    public double getTotalPrice(){
        return quantity * price;
    }


}
