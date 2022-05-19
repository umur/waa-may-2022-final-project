package pro.manage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE propertypicture SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class PropertyPicture extends Audit{
    private String path;

    @ManyToOne
    private Property property;
}
