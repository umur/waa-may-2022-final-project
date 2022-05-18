package waa.propertymanagementbackend.dto;

import lombok.Data;
import waa.propertymanagementbackend.domain.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
@Data
public class EmailDataDetailDto {

    private int id;
    private String subject;

    private String messageBody;



    private UserDto receiver;

    private LocalDate sendDate;
    private boolean status;
}
