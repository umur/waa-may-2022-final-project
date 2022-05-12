package pro.manage.entity.dto;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long addressId;
    private String houseNumber;
    private String street;
    private Integer zipCode;
    private String city;
    private String  state;

}
