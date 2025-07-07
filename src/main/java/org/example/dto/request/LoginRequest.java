package org.example.dto.request;

import lombok.Data;

@Data
public class LoginRequest extends UserDto {
    private String token;
}
