package waa.project.finalproj.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean active = true;
    private LocalDate deletedAt;
    private LocalDateTime LastLoggedInAt;
    private String role;

//    @OneToOne(mappedBy = "user")
//    private Landlord landlord;

//    @OneToOne(mappedBy = "user")
//    private Admin admin;

//    @OneToOne(mappedBy = "userId")
//    private Tenant tenant;

    @OneToMany(mappedBy = "user")
    private List<Rent> user;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "property")
    private List<Property> property;
}
