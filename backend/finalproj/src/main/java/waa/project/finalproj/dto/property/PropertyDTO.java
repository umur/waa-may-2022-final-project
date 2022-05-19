package waa.project.finalproj.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.Rent.RentBasicDTO;
import waa.project.finalproj.dto.propertyType.PropertyTypeDTO;
import waa.project.finalproj.dto.user.UserAttrOnlyDTO;
import waa.project.finalproj.entity.Photo;

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

    private PropertyTypeDTO propertyType;

    private UserAttrOnlyDTO user;

    private List<RentBasicDTO> rent;
}
