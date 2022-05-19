package waa.propertymanagementbackend.dto;

import lombok.Data;
import waa.propertymanagementbackend.domain.Role;

import java.time.LocalDate;
@Data
public class UserSignupDto {

    private int id;

    private String email;
    private String firstName;
    private String lastname;
    private String password;

    private Role role;

    private LocalDate LastLoggedInAt;
    private Boolean active;
    private Boolean deleted;

    private LocalDate createdDate;
}
