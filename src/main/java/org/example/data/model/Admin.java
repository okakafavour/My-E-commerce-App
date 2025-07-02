package org.example.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Permission;
import java.util.List;

@Data
@Document(collection = "admins")
public class Admin extends User{
    private String fullName;
    private List<Permission> permissions;


}
