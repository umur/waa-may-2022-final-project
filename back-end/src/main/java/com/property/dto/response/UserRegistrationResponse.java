package com.property.dto.response;

import com.property.domain.Role;
import lombok.Data;

@Data
public class UserRegistrationResponse {

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

}