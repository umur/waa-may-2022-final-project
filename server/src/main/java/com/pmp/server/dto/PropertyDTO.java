package com.pmp.server.dto;

import com.pmp.server.domain.PropertyImage;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    private String propertyName;
    private String description;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double rentAmount;
    private double securityDepositAmount;
    private boolean isOccupied;
    private List<PropertyImage> photos;
    private boolean active;
}
