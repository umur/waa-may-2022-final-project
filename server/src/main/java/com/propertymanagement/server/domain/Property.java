package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean delete;

    @OneToMany
    private List<Image> images;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RentalActivity> rentalActivities;

    @ManyToOne
    private User ownedBy;

    @ManyToOne
    private User tenant;

    public void addImage(Image image){
        this.images.add(image);
    }

    public void addImages(List<Image> images){
        images.forEach(image -> image.setProperty(this));
        this.images = new ArrayList<>();
        this.images.addAll(images);
    }
}
