package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "user_id",allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "ownedBy")
    private List<Property> properties;

    public void addProperty(Property property){
        this.properties.add(property);
    }

    @OneToMany(mappedBy = "tenant")
    private List<Property> rentedProperties;

    public void addRentedProperties(Property property){
        this.rentedProperties.add(property);
    }
}
