package org.example.dto.response;

import lombok.Data;

@Data
public class CustomerLoginResponse {
    private String email;
    private String password;
}
