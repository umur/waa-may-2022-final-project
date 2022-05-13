package waa.project.finalproj.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.propertyType.PropertyTypeOnlyAttrDTO;
import waa.project.finalproj.dto.user.UserWithoutPropertyDTO;
import waa.project.finalproj.entity.Photo;
import waa.project.finalproj.entity.Rent;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
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

    private List<Photo> photos;

    private PropertyTypeOnlyAttrDTO propertyType;

    private UserWithoutPropertyDTO user;

    private List<Rent> rent;
}
