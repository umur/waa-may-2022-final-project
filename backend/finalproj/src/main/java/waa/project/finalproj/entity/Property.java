package waa.project.finalproj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double rentAmount;
    private double securityDepositAmount;
    private boolean occupied;
    private boolean listed;
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Photo> photos;

    @ManyToOne
    @JsonBackReference
    private PropertyType propertyType;

    @OneToMany(mappedBy = "property")
    @JsonManagedReference(value = "rent")
    private List<Rent> rent;

    @ManyToOne
    @JsonBackReference(value = "property")
    private User user;
}
