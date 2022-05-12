package pro.manage.entity.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
public class PropertyDto {

    private Long houseId;
    private Double price;
    private String houseType;
    private Integer numberOfRooms;
    private String description;

}
