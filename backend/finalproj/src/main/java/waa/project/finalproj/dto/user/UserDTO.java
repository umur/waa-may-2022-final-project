package waa.project.finalproj.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.Rent.RentOnlyAttrDTO;
import waa.project.finalproj.dto.property.PropertyAttrOnly;

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

    private List<RentOnlyAttrDTO> rents;
    private List<PropertyAttrOnly> property;
}
