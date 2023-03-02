package com.psut.validators;

import com.psut.models.student.Student;
import com.psut.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class UsernameValidator {
    private final StudentRepository studentRepository;

    public void validate(Student student, Set<String> violations) {
        //TODO: filter listed students to whom have same username
        List<Student> similarStudent = studentRepository.findAll();
        if(Objects.nonNull(similarStudent) && !similarStudent.isEmpty() && !similarStudent.get(0).getId().equals(student.getId())) {
            violations.add("username.is.used");
        }
    }
}
