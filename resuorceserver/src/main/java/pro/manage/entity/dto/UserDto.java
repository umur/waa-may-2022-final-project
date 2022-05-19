package pro.manage.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private boolean active;
    private LocalDateTime LastLoggedInAt;
    private String role;

    private List<RentOnlyAttrDTO> rents;
    private List<PropertyAttrOnly> property;
}
