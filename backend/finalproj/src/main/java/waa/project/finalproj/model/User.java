package waa.project.finalproj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private boolean active;
    private LocalDate deleted_at;
    private LocalDateTime LastLoggedInAt;

    @OneToOne(mappedBy = "user")
    private Landlord landlord;

    @OneToOne(mappedBy = "user")
    private Admin admin;

    @OneToOne(mappedBy = "userId")
    private Tenant tenant;
}
