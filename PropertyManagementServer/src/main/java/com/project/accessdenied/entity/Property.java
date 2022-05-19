package com.project.accessdenied.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@CrossOrigin
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private float rentAmount;
    private float securityDepositAmount;
    private boolean isOccupied;
    private LocalDate lastRentedDate;
    private LocalDate endDate;
    private Boolean isDeleted = Boolean.FALSE;

    @ElementCollection
    private List<String> photos;
    @ManyToOne
    private User ownedBy;
    @OneToOne
    private User lastRentedBy;

    @OneToOne
    private City city;
    //addition

    @OneToOne
    PropertyType propertyType;

}
