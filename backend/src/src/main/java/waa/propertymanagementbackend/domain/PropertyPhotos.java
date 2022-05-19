package waa.propertymanagementbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "property_photos")
@Entity
@Data
public class PropertyPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "photo_url")
    private String photoUrl;
}
