package waa.propertymanagementbackend.dto;

import waa.propertymanagementbackend.domain.Role;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class UserSignup {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String firstName;
    private String lastname;
    private String password;
    @OneToOne
    private Role role;

    private LocalDate LastLoggedInAt;
    private Boolean active;
    private Boolean deleted;

    private LocalDate createdDate;
}
