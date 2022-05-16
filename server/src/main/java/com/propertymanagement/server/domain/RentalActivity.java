package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class RentalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "rentalactivity_id",allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="rented_by")
    private User rentedBy;

    @ManyToOne
    private Property property;

    @ManyToOne
    @JoinColumn(name="tenant_id")
    private User tenant;

    private LocalDate startDate;

    private LocalDate endDate;

}
