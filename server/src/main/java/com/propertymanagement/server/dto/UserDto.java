package com.propertymanagement.server.dto;

import com.propertymanagement.server.domain.Role;
import lombok.Data;

@Data
public class UserDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

}
