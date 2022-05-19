package com.project.accessdenied.dto;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
