package pro.manage.entity;

import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@SQLDelete(sql = "UPDATE lease SET isdeleted = true WHERE id=?")
public class Lease extends  Audit{

    private double rent;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;


    @OneToOne
    private Property property;

    @OneToOne
    private Tenant tenant;

}
