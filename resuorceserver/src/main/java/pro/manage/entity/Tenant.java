package pro.manage.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@SQLDelete(sql = "UPDATE tenant SET isdeleted = true WHERE id=?")
public class Tenant extends Audit {

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
