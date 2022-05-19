package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

    private UUID id;
    private String name;
    private Double rent;
    private int nobedrooms;
    private int nobathrooms;
    private String description;
    private boolean islisted;
    private boolean isOccupied;
    private int deposit;
    private List<String> images;


}
