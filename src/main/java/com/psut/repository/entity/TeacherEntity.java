package com.psut.repository.entity;

import com.psut.model.shared.Gender;
import com.psut.model.shared.User;
import com.psut.model.teacher.TeachingWay;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "teacher")
public class TeacherEntity extends User {
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;
    @ElementCollection(targetClass = Gender.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "targeted_students")
    private List<Gender> targetedStudents;
    @ElementCollection(targetClass = TeachingWay.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "teaching_way")
    private List<TeachingWay> teachingWay;

    @Column(name = "rate_avg")
    private Double rateAvg;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialEntity> materials;

    public void setReferenceForAllMaterials() {
        materials.forEach(material -> material.setTeacher(this));
    }
}
