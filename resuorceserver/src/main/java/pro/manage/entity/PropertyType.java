package pro.manage.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE propertytype SET isdeleted = true WHERE id=?")
public class PropertyType extends  Audit{

    String name;

    @OneToMany(mappedBy = "type")
    private List<Property> properties;
}
