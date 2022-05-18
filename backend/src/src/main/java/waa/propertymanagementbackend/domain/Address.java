package waa.propertymanagementbackend.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private int id;

    private String street;
    private String city;
    private String State;
    private int zipCode;


}
