package com.psut.controller;

import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.service.StudentService;
import com.psut.repository.specification.StudentSpecifications;
import com.psut.domain.usecase.student.ActivateStudentUseCase;
import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.domain.usecase.student.DeactivateStudentUseCase;
import com.psut.domain.usecase.student.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controller.StudentsController.STUDENTS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(STUDENTS_BASE_URL)
public class StudentsController {
    public static final String STUDENTS_BASE_URL = "/api/v1/students";

    private final StudentService studentService;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeactivateStudentUseCase deactivateStudentUseCase;
    private final ActivateStudentUseCase activateStudentUseCase;

    @GetMapping
    public List<Student> listStudents(StudentSpecifications specifications, Sort sort) {
        return studentService.findAll(specifications, sort);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return createStudentUseCase.execute(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateRequest) {
        Student student = studentService.findById(id);
        return updateStudentUseCase.execute(student, updateRequest);
    }

    @PostMapping("{id}/deactivate")
    public Student deactivateStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return deactivateStudentUseCase.execute(student);
    }

    @PostMapping("{id}/activate")
    public Student ActivateStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return activateStudentUseCase.execute(student);
    }

}
