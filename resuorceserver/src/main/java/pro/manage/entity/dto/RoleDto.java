package pro.manage.entity.dto;
import lombok.Data;

import javax.persistence.*;


@Data
public class RoleDto {

    private Long roleId;
    private String  role;

}
