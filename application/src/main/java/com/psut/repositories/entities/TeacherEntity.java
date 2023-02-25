package com.psut.repositories.entities;

import com.psut.models.teacher.TargetedGender;
import com.psut.models.teacher.TeachingWay;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity(name = "teacher")
public class TeacherEntity extends UserEntity {
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    private String branch;
    private String subject;
    @Column(name = "targeted_students")
    private TargetedGender targetedStudents;
    @Column(name = "teaching_way")
    private TeachingWay teachingWay;
    @Column(name = "price_per_hour")
    private BigDecimal pricePerHour;
    @Column(name = "price_per_course")
    private BigDecimal pricePerCourse;
}
