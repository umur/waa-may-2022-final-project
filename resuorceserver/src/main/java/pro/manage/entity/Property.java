package pro.manage.entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Property extends  Audit{

    private String name;
    private Double rent;
    private int nobedrooms;
    private int nobathrooms;
    private String description;
    private boolean islisted;
    private boolean isOccupied;
    private int deposit;


    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne
    private Type type;

    @ManyToOne(cascade = CascadeType.ALL)
    private  Landlord owner;

    @OneToOne
    private Tenant lastTenant; //last Tenant rent this property

    @OneToMany(mappedBy = "property")
    private List<Picture> photos;


}
