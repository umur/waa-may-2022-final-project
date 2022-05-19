package waa.propertymanagementbackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import waa.propertymanagementbackend.domain.Address;
import waa.propertymanagementbackend.domain.PropertyPhotos;
import waa.propertymanagementbackend.domain.PropertyType;
import waa.propertymanagementbackend.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

//@Data
@Getter
@Setter
public class PropertyDto {
    private int id;

    private String propertyName;

    private Address address;

    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int rentAmount;
    private List<PropertyPhotos> propertyPhotos;
    private Boolean isOccupied;
    private UserDto ownedBy;
    private UserDto lastRentedBy;
    private int securityDepositAmount;
    private Boolean deleted;


}
