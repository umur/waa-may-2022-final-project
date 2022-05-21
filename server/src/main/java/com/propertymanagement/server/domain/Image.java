package com.propertymanagement.server.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "image_id",allocationSize = 1)
    private Long id;

    private boolean isDeleted;

    @Column(length = 2732)
    private String fileContentBase64;

    private String fileName;

    @ManyToOne
    private Property property;
}
