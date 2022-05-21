package com.propertymanagement.server.dto;

import com.propertymanagement.server.domain.PropertyType;
import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
    private Long id;

    private String propertyName;

    private String streetAddress;

    private String city;

    private String state;

    private int zipCode;

    private PropertyType propertyType;

    private int numberOfBedrooms;

    private int numberOfBathrooms;

    private double rentAmount;

    private double securityDepositAmount;

//    private List<ImageDto> images;

}
