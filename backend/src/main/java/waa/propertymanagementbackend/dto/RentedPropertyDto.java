package waa.propertymanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class RentedPropertyDto {
    private PropertyDto property;
    private UserDto rentedBy;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private int rentedAmount;



}
