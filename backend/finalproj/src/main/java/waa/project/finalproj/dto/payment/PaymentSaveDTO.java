package waa.project.finalproj.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.entity.Rent;
import waa.project.finalproj.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSaveDTO {
    private int id;
    private double amount;
    private LocalDate date;
    private LocalDate referentDate;
    private User user;
    private Rent rent;
}
