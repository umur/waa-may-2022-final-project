package pro.manage.entity.dto;

import lombok.Data;


@Data
public class UserRegistrationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String  role;

    private boolean isActive;

    private String email;

}
