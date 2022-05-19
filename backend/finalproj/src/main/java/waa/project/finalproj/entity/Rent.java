package waa.project.finalproj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JsonBackReference(value = "rentsUser")
    private User user;

    @OneToMany(mappedBy = "rent")
    private List<Payment> payment;

    @ManyToOne
    @JsonBackReference(value = "rent")
    private Property property;
}
