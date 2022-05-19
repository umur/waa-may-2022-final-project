package pro.manage.entity.dto;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private UUID addressId;
    private String houseNumber;
    private String street;
    private Integer zipCode;
    private String city;
    private String  state;

}
