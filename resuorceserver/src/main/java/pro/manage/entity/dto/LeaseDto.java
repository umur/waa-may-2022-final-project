package pro.manage.entity.dto;
import lombok.Data;
import pro.manage.entity.Audit;

import javax.persistence.*;
import java.time.LocalDate;


@Data
public class LeaseDto  {
    private Long bookingRecordId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice;

}


