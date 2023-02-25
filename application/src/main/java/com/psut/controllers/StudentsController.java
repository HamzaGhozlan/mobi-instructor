package com.psut.controllers;

import com.psut.models.student.Student;
import com.psut.models.student.UpdateStudentRequest;
import com.psut.repositories.StudentRepository;
import com.psut.usecases.students.CreateStudentUseCase;
import com.psut.usecases.students.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.psut.controllers.StudentsController.STUDENTS_BASE_URL;

@RequiredArgsConstructor
@RestController(STUDENTS_BASE_URL)
public class StudentsController {
    public static final String STUDENTS_BASE_URL = "/api/v1/students";

    private final StudentRepository studentRepository;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;

    @PostMapping
    public Student createStudent(Student student) {
        return createStudentUseCase.execute(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateRequest) {
        Student student = studentRepository.findById(id);
        return updateStudentUseCase.execute(student, updateRequest);
    }
}
