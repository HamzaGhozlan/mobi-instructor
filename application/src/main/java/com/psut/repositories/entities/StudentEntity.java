package com.psut.repositories.entities;

import jakarta.persistence.Entity;

@Entity(name = "student")
public class StudentEntity extends UserEntity {
    private String email;
}
