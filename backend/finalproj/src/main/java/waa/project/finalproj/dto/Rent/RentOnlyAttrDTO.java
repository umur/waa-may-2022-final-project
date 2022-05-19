package waa.project.finalproj.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import waa.project.finalproj.dto.property.PropertyAttrOnly;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentOnlyAttrDTO {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyAttrOnly property;
}
