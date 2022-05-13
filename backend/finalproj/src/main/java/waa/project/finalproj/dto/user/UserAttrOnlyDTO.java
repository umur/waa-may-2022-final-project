package waa.project.finalproj.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttrOnlyDTO {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private boolean active;
    private LocalDateTime LastLoggedInAt;
    private String role;
}
