package com.property.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "users_id_seq",allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
