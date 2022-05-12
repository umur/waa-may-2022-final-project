package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.manage.entity.Audit;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseDto  {
    private Long bookingRecordId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice;

}


