package com.project.accessdenied.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@CrossOrigin
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;


}
