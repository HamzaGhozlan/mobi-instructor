package com.psut.usecases.students;

import com.psut.models.student.Student;
import com.psut.repositories.StudentRepository;
import com.psut.validators.StudentValidator;
import com.psut.validators.exceptions.BusinessValidationException;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CreateStudentUseCase {
    private final StudentValidator studentValidator;
    private final StudentRepository studentRepository;

    public Student execute(Student student) {
        Set<String> violations = studentValidator.validate(student);
        if(!violations.isEmpty()){
            throw new BusinessValidationException(violations);
        }
        return studentRepository.save(student);
    }

}
