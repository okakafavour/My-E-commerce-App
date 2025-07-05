package org.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Status;
import org.springframework.data.annotation.Transient;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
