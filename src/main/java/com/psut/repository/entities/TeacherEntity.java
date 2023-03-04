package com.psut.repository.entities;

import com.psut.model.shared.Gender;
import com.psut.model.shared.UserStatus;
import com.psut.model.teacher.TargetedGender;
import com.psut.model.teacher.TeachingWay;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "teacher")
public class TeacherEntity {
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
    private UserStatus status;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    private String branch;
    private String subject;
    @Column(name = "targeted_students")
    @Enumerated(EnumType.STRING)
    private TargetedGender targetedStudents;
    @Column(name = "teaching_way")
    @Enumerated(EnumType.STRING)
    private TeachingWay teachingWay;
    @Column(name = "price_per_hour")
    private BigDecimal pricePerHour;
    @Column(name = "price_per_course")
    private BigDecimal pricePerCourse;
}
