package pro.manage.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {
    private int id;
    private String email;
    private String password;
    private String role;
    private String firstname;
    private String lastname;
}
