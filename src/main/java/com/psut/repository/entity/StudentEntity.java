package com.psut.repository.entity;

import com.psut.model.shared.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "student")
public class StudentEntity extends User {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_favorite_teacher",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    private List<TeacherEntity> favoriteTeachers;
}
