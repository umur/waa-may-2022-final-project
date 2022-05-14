package com.property.dto;

import lombok.Data;

@Data
public class PropertyDto {

    private Long id;

    private String propertyName;

    private String propertyType;

    private Integer noOfBedRoom;

    private Integer noOfBathRoom;

    private Double rentAmount;

    private Double securityDepositAmount;

}
