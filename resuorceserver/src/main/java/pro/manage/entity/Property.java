package pro.manage.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE property SET isdeleted = true WHERE id=?")
@Where(clause = "isdeleted=false")
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
    private PropertyType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private  Landlord owner;

    @OneToOne
    private Tenant lastTenant; //last Tenant rent this property

    @OneToMany(mappedBy = "property" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private List<PropertyPicture> photos;

    public List<PropertyPicture> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PropertyPicture> photos) {
        this.photos = photos;
    }
}
