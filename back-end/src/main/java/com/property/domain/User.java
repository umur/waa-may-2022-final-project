package com.property.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "users_id_seq",allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "landLord")
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
