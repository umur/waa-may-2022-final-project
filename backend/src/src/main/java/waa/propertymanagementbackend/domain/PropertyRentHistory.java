package waa.propertymanagementbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="property_rent_history")
public class PropertyRentHistory {
    @javax.persistence.Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="serial")
    private int Id;

    @OneToOne
   private Property property;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private int securityDepositAmount;
    private int rentedAmount;

    @OneToOne
    private User rentedBy;
    private Boolean active;
}
