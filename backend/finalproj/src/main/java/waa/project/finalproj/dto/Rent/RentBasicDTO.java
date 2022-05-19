package waa.project.finalproj.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.payment.PaymentAttrDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentBasicDTO {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<PaymentAttrDTO> paymentList;
}
