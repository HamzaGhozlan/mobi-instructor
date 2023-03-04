package com.psut.model.teacher;

import com.psut.model.shared.Gender;
import com.psut.model.shared.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Teacher extends User {
    private String phoneNumber;
    private String city;
    private List<Gender> targetedStudents;
    private List<TeachingWay> teachingWay;
    private Double rateAvg;
}
