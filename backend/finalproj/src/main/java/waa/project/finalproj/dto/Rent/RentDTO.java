package waa.project.finalproj.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.payment.PaymentAttrDTO;
import waa.project.finalproj.dto.property.PropertyAttrOnly;
import waa.project.finalproj.dto.user.UserAttrOnlyDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentDTO {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;

    private UserAttrOnlyDTO user;

    private PropertyAttrOnly property;

    private List<PaymentAttrDTO> paymentList;
}
