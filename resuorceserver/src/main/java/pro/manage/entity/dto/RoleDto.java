package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private UUID id;
    private String  role;

}
