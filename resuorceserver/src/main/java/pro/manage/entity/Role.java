package pro.manage.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
@Data
public class Role extends  Audit{

    private String  role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
