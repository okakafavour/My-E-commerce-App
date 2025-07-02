package org.example.data.model;

import lombok.Data;
import org.example.enums.Status;

import java.beans.Transient;

@Data
public class Item {
    private String productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private Status status;

    @Transient
    public double TotalPrice(){
        return quantity * price;
    }
}
