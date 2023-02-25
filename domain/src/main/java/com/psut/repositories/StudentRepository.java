package com.psut.repositories;

import com.psut.models.student.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student student);
    Student findById(Long id);
    List<Student> findAll();
}
