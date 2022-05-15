package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "photo_id",allocationSize = 1)
    private Long id;

    private String url;

    private boolean isDeleted;

    @ManyToOne
    private Property property;
}
