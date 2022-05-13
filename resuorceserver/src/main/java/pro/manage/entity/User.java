package pro.manage.entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Entity
@Setter
@Getter
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET isdeleted = true WHERE id=?")
public class User extends  Audit {

    private String firstName;
    private String lastname;
    //@Size(min=10,max=10,message="{size.phoneNo}")
    //@Pattern(regexp="(^$|[0-9]{10})")
    private String phoneno;
    @NotEmpty(message = "username is required")
    @Email
    private String username;
    private String password;


    @ManyToOne
    private Role role;
    @OneToOne(mappedBy = "user")
    private Tenant tenant;

    @OneToOne(mappedBy = "user")
    private Landlord landlord;



}
