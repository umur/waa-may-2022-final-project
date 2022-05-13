package pro.manage.entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE adress SET isdeleted = true WHERE id=?")
public class Address extends  Audit{

    @Column(nullable = false)
    private String block;
    private String street;
    private int zipcode;
    private String city;
    private String  state;
    @OneToOne(mappedBy = "address")
    private Tenant tenant;

    @OneToOne(mappedBy = "address")
    private Landlord landlord;

    @OneToOne(mappedBy = "address")
    private Property property;

}
