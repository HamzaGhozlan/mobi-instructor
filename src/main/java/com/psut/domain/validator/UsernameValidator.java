package com.psut.domain.validator;

import com.psut.model.student.Student;
import com.psut.repository.impl.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class UsernameValidator {
    private final StudentRepository studentRepository;

    public void validate(Student student, Set<String> violations) {
        Student example = new Student();
        example.setUsername(student.getUsername());
        List<Student> similarStudent = studentRepository.findAll(example);

        if (Objects.nonNull(similarStudent) && !similarStudent.isEmpty() && !similarStudent.get(0).getId().equals(student.getId())) {
            violations.add("username.is.used");
        }
    }
}
