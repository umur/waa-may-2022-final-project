package com.project.accessdenied.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Data
@CrossOrigin
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
}
