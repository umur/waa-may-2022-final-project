package com.property.dto;

import lombok.Data;

@Data
public class UserRegistration {

    private String firstName;

    private String lastName;

    private String role;

    private String email;

    private String password;
}
