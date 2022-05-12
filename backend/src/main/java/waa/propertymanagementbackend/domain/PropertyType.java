package waa.propertymanagementbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "property_type")
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
}
