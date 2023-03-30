package com.psut.model.teacher;

import com.psut.model.Material;
import com.psut.model.shared.Gender;
import com.psut.model.shared.User;
import lombok.Data;

import java.util.List;

@Data
public class Teacher extends User {
    private String description;
    private String phoneNumber;
    private String city;
    private List<Gender> targetedStudents;
    private List<TeachingWay> teachingWay;
    private Double rateAvg;

    private List<Material> materials;
}
