package pro.manage.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User extends  Audit {

    private String firstName;
    private String lastname;
    //@Size(min=10,max=10,message="{size.phoneNo}")
    //@Pattern(regexp="(^$|[0-9]{10})")
    private String phoneno;
    private String username;
    private String password;


    @ManyToOne
    private Role role;
    @OneToOne(mappedBy = "user")
    private Tenant tenant;

    @OneToOne(mappedBy = "user")
    private Landlord landlord;



}
