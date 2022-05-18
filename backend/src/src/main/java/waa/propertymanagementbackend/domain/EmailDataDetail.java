package waa.propertymanagementbackend.domain;

import lombok.Data;
import waa.propertymanagementbackend.dto.UserDto;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="email_messages")
public class EmailDataDetail {
    @Id
    private int id;
    private String subject;
    @Column(name = "message_body")
    private String messageBody;


    @OneToOne
    private User receiver;

    private LocalDate sendDate;
    private boolean status;

}
