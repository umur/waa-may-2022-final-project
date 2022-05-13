package com.property.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "property_id_seq",allocationSize = 1)
    private Long id;

    private String propertyName;

    private String propertyType;

    private Integer noOfBedRoom;

    private Integer noOfBathRoom;

    private Double rentAmount;

    private Double securityDepositAmount;

    @OneToMany(mappedBy = "property")
    private List<Photo> photos;

    @ManyToOne
    private User landLord;

    @ManyToOne
    private User tenant;



}
