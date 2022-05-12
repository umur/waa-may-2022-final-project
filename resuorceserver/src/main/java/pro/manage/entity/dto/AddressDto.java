package pro.manage.entity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
public class AddressDto {

    private Long addressId;
    private String houseNumber;
    private String street;
    private Integer zipCode;
    private String city;
    private String  state;

}
