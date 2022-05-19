package waa.propertymanagementbackend.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String firstName;
    private String lastname;
    private String password;
    @OneToOne
    private Role role;

    private LocalDate LastLoggedInAt;
    private Boolean active;
    private Boolean deleted;

private LocalDate createdDate;
}
