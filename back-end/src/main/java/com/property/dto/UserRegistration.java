package com.property.dto;

import com.property.domain.Role;
import lombok.Data;

@Data
public class UserRegistration {

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

    private String password;
}
