package pro.manage.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@SQLDelete(sql = "UPDATE propertypicture SET isdeleted = true WHERE id=?")
public class PropertyPicture extends Audit{
    private String path;

    @ManyToOne
    private Property property;
}
