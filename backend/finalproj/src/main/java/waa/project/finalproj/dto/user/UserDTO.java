package waa.project.finalproj.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.entity.Property;
import waa.project.finalproj.entity.Rent;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private boolean active;
    private LocalDateTime LastLoggedInAt;
    private String role;

    private List<Rent> user;
    private List<Property> property;
}
