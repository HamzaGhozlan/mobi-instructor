package com.psut.controller;

import com.psut.domain.usecase.student.ActivateStudentUseCase;
import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.domain.usecase.student.DeactivateStudentUseCase;
import com.psut.domain.usecase.student.UpdateStudentUseCase;
import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.repository.specification.StudentSpecifications;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public Page<Student> listStudents(StudentSpecifications specifications, Pageable pageable) {
        return studentService.findAll(specifications, pageable);
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
        return updateStudentUseCase.execute(id, updateRequest);
    }

    @PostMapping("{id}/deactivate")
    public Student deactivateStudent(@PathVariable Long id) {
        return deactivateStudentUseCase.execute(id);
    }

    @PostMapping("{id}/activate")
    public Student ActivateStudent(@PathVariable Long id) {
        return activateStudentUseCase.execute(id);
    }

}
