package com.psut.repositories.entities;

import com.psut.models.shared.Gender;
import jakarta.persistence.*;


@Entity
@Inheritance
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Gender gender;
    private String username;
    private String password;
}
