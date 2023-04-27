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
    private String description;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String city;

    @ElementCollection(targetClass = Gender.class)
    @CollectionTable(name = "teacher_targeted_students", joinColumns = @JoinColumn(name = "teacher_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "targeted_student")
    private List<Gender> targetedStudents;

    @ElementCollection(targetClass = TeachingWay.class)
    @CollectionTable(name = "teacher_teaching_way", joinColumns = @JoinColumn(name = "teacher_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "teaching_way")
    private List<TeachingWay> teachingWays;

    @Column(name = "rate_avg")
    private Double rateAvg;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialEntity> materials;

    public void setReferenceForAllMaterials() {
        materials.forEach(material -> material.setTeacher(this));
    }
}
