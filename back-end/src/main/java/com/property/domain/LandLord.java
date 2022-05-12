package com.property.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class LandLord extends User{

    @OneToMany(mappedBy = "landLord")
    private List<Property> properties;

    public void addProperty(Property property){
        this.properties.add(property);
    }

}
