package org.example.dto.response;

import lombok.Data;
import org.example.enums.Role;

@Data
public class RegisterResponse {
    private String message;
    private Role role;
}
