package pro.manage.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAttrOnly {
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double rentAmount;
    private double securityDepositAmount;
    private boolean occupied;
    private boolean listed;

    private List<String> photos;

    private String propertyType;
}
