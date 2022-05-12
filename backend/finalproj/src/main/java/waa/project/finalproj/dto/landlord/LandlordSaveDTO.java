package waa.project.finalproj.dto.landlord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.user.UserSaveDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandlordSaveDTO {
    private int id;
    private String firstname;
    private String lastname;
    private UserSaveDTO user;
}
