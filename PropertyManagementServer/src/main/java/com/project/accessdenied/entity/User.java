package com.project.accessdenied.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastname;
    private String password;
    private LocalDateTime lastLoggedInAt;
    private boolean isActive;

    @OneToMany(mappedBy = "ownedBy")
    @JsonIgnore
    private List<Property> properties;

    @OneToOne
    private Role role;

}
