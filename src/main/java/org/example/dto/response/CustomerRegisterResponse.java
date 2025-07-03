package org.example.dto.response;

import lombok.Data;
import org.example.dto.request.UserDto;

@Data
public class CustomerRegisterResponse extends UserDto {
    private String customerId;
    private String address;
}
