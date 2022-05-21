package com.propertymanagement.server.dto;

import com.propertymanagement.server.domain.Role;
import lombok.Data;

@Data
public class UserRespDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}