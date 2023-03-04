package com.psut.model.teacher;

import com.psut.model.shared.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Teacher extends User {
    private String email;
    private String phoneNumber;
    private String city;
    private String branch;
    private String subject;
    private TargetedGender targetedStudents;
    private TeachingWay teachingWay;
    private BigDecimal pricePerHour;
    private BigDecimal pricePerCourse;
}
