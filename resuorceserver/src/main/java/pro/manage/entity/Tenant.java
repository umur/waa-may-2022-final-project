package pro.manage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE tenant SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class Tenant extends Audit {

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
