package com.psut.repositories;

import com.psut.models.student.Student;

public interface StudentRepository {
    Student save(Student student);
    Student findById(Long id);
}
