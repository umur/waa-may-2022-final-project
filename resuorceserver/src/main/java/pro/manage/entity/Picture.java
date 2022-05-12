package pro.manage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Picture extends Audit{
    private String path;

    @ManyToOne
    private Property property;
}
