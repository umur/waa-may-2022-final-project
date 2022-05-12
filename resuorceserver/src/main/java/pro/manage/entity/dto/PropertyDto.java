package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

    private Long houseId;
    private Double price;
    private String houseType;
    private Integer numberOfRooms;
    private String description;

}
