package waa.project.finalproj.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.entity.Property;
import waa.project.finalproj.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentSaveDTO {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;

    private User user;

    private Property property;
}
