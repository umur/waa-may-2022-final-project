package waa.propertymanagementbackend.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Setter
@Getter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String propertyName;
    @OneToOne
    private Address address;
    @OneToOne
    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private float rentAmount;

    private int securityDepositAmount;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private List<PropertyPhotos> propertyPhotos;

    @OneToOne
    @JoinColumn(name="owned_by")
    private User ownedBy;

    @OneToOne
    @JoinColumn(name="last_rented_by")
    private User lastRentedBy;

    private Boolean isOccupied;
    private Boolean visible;

    private Boolean deleted;



}
