package waa.project.finalproj.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.Rent.RentOnlyAttrDTO;
import waa.project.finalproj.dto.user.UserAttrOnlyDTO;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int id;
    private double amount;
    private LocalDate date;
    private LocalDate referentDate;
    private UserAttrOnlyDTO user;
    private RentOnlyAttrDTO rent;
}
