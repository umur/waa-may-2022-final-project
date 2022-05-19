package pro.manage.entity.dto;


import lombok.Data;


@Data
public class UserRegistrationRequest {

    private String firstName;

    private String lastName;

    private String role;

    private String email;

    private String password;
}
