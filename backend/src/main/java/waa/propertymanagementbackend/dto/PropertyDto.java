package waa.propertymanagementbackend.dto;

import waa.propertymanagementbackend.domain.Address;
import waa.propertymanagementbackend.domain.PropertyPhotos;
import waa.propertymanagementbackend.domain.PropertyType;

import java.util.List;

public class PropertyDto {
    private int Id;

    private String propertyName;

    private Address address;

    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int rentAmount;



    //private List<PropertyPhotos> propertyPhotos;


    private Boolean isOccupied;
}
