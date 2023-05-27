package com.psut.model.teacher;

import com.psut.model.shared.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateTeacherRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String city;
    private List<Gender> targetedStudents;
    private List<TeachingWay> teachingWays;
    private List<Material> materials;

    public Teacher applyUpdatesOn(Teacher teacher) {
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUsername(username);
        teacher.setPassword(password);
        teacher.setEmail(email);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setCity(city);
        teacher.setTargetedStudents(targetedStudents);
        teacher.setTeachingWays(teachingWays);
        teacher.setMaterials(materials);
        return teacher;
    }
}
