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

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE landlord SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class Landlord extends Audit {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Property> properties;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
