package com.psut.controllers;

import com.psut.models.student.Student;
import com.psut.models.student.UpdateStudentRequest;
import com.psut.repositories.StudentRepository;
import com.psut.repositories.adapters.StudentRepositoryAdapter;
import com.psut.repositories.specifications.StudentSpecifications;
import com.psut.usecases.student.ActivateStudentUseCase;
import com.psut.usecases.student.CreateStudentUseCase;
import com.psut.usecases.student.DeactivateStudentUseCase;
import com.psut.usecases.student.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controllers.StudentsController.STUDENTS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(STUDENTS_BASE_URL)
public class StudentsController {
    public static final String STUDENTS_BASE_URL = "/api/v1/students";

    private final StudentRepository studentRepository;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeactivateStudentUseCase deactivateStudentUseCase;
    private final ActivateStudentUseCase activateStudentUseCase;
    private final StudentRepositoryAdapter repositoryAdapter;

    @GetMapping
    public List<Student> listStudents(StudentSpecifications specifications) {
        return studentRepository.findAll(specifications);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
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

    @PostMapping("{id}/deactivate")
    public Student deactivateStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id);
        return deactivateStudentUseCase.execute(student);
    }

    @PostMapping("{id}/activate")
    public Student ActivateStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id);
        return activateStudentUseCase.execute(student);
    }

}
