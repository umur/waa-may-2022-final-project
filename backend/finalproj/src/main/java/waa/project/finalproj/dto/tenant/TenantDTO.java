package waa.project.finalproj.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.user.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO {
    private int id;
    private String firstname;
    private String lastname;
    private boolean active;
    private UserDTO user;
}
