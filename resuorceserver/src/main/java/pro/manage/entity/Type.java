package pro.manage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Type extends  Audit{

    String name;

    @OneToMany(mappedBy = "type")
    private List<Property> properties;
}
