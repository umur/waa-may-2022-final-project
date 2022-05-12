package waa.propertymanagementbackend.domain;


import lombok.Data;


import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String propertyName;
    @OneToOne
    private Address address;
    @OneToOne
    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int rentAmount;
    private int securityDepositAmount;

    @OneToMany
    @JoinColumn(name = "property_id")
    private List<PropertyPhotos> propertyPhotos;

    @OneToOne
    private Users ownedBy;
    @OneToOne
    private Users lastRentedBy;
    private Boolean isOccupied;
    private Boolean visible;
    private Boolean deleted;


}
