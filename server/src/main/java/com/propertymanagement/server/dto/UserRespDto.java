package com.propertymanagement.server.dto;

import com.propertymanagement.server.domain.Role;
import lombok.Data;

@Data
public class UserRespDto {
    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}
