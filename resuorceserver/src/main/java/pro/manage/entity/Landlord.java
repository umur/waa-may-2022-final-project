package pro.manage.entity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE landlord SET isdeleted = true WHERE id=?")
public class Landlord extends Audit {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Property> properties;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
