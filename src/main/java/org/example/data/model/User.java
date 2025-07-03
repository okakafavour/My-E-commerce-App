package org.example.data.model;

import lombok.Data;
import org.example.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public abstract class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private boolean enable;

}
