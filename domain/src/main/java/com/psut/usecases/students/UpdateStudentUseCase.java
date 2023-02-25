package com.psut.usecases.students;

import com.psut.models.student.Student;
import com.psut.models.student.UpdateStudentRequest;
import com.psut.repositories.StudentRepository;
import com.psut.validators.StudentValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStudentUseCase {
    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    public Student execute(Student student, UpdateStudentRequest updateRequest) {
        updateRequest.applyUpdatesOn(student);
        studentValidator.validate(student);
        return studentRepository.save(student);
    }
}
