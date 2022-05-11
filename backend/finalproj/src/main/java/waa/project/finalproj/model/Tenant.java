package waa.project.finalproj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private boolean active;
    private LocalDate deleted_at;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToMany(mappedBy = "tenantId")
    private List<Rent> rent;
}
