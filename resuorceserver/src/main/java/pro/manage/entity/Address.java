package pro.manage.entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE address SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
public class Address extends  Audit{

    @Column(nullable = false)
    private String block;
    private String street;
    private int zipcode;
    private String city;
    private String  state;
    @OneToOne(mappedBy = "address")
    private User user;

    @OneToOne(mappedBy = "address")
    private Property property;

}
