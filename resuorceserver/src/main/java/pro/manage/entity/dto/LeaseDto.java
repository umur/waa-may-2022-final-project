package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.manage.entity.Audit;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseDto  {
    private UUID id;
    private  double rent;
    private LocalDate startDate;
    private LocalDate endDate;

}


