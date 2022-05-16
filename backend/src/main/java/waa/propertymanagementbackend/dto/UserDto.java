package waa.propertymanagementbackend.dto;

import lombok.Data;
import waa.propertymanagementbackend.domain.Role;

@Data
public class UserDto {


    private int id;

    private String email;
    private String firstName;
    private String lastname;
    private Role role;
}
