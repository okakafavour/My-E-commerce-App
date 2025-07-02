package org.example.dto.response;

import lombok.Data;

@Data
public class CustomerRegisterResponse {
    private String customerId;
    private String email;
    private String phoneNumber;
    private String address;
}
