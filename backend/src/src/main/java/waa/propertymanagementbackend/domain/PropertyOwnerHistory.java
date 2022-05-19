package waa.propertymanagementbackend.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class PropertyOwnerHistory {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="serial")
    private int Id;

    @OneToOne
    private Property property;
    private LocalDate ownedFrom;
    @Nullable
    private LocalDate OwnedTo;

    @OneToOne
    private User ownedBy;
    private Boolean active;
}
