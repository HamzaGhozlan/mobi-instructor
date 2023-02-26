package com.psut.repositories.entities;

import com.psut.models.shared.Gender;
import com.psut.models.shared.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
}
