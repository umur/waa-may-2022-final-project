package pro.manage.entity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
@Data
@SQLDelete(sql = "UPDATE role SET isdeleted = true WHERE id=?")
public class Role extends  Audit{

    private String  role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
