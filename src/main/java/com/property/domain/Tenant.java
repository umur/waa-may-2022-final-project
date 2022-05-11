package com.property.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Tenant extends User {

    @OneToMany(mappedBy = "tenant")
    private List<Property> rentedProperties;

    public void addRentedProperties(Property property){
        this.rentedProperties.add(property);
    }

}
