package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "property_id",allocationSize = 1)
    private Long id;

    private String propertyName;

    private String streetAddress;

    private String city;

    private String state;

    private int zipCode;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    private int numberOfBedrooms;

    private int numberOfBathrooms;

    private double rentAmount;

    private double securityDepositAmount;

    private boolean isOccupied;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RentalActivity> rentalActivities;

    @ManyToOne
    private User ownedBy;

    @ManyToOne
    private User tenant;

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }

    public void addPhotos(List<Photo> photos){
        photos.forEach(photo -> photo.setProperty(this));
        this.photos = new ArrayList<>();
        this.photos.addAll(photos);
    }
}
