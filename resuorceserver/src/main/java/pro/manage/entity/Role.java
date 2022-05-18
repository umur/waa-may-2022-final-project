package pro.manage.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
@Getter
@Setter
@SQLDelete(sql = "UPDATE role SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class Role extends  Audit{

    private String  role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
