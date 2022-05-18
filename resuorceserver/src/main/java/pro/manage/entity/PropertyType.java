package pro.manage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE propertytype SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class PropertyType extends  Audit{

    String name;

    @OneToMany(mappedBy = "type")
    private List<Property> properties;
}
