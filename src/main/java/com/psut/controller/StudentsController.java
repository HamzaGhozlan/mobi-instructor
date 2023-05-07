package com.psut.controller;

import com.psut.domain.usecase.student.*;
import com.psut.model.student.Student;
import com.psut.model.student.UpdateStudentRequest;
import com.psut.model.teacher.Teacher;
import com.psut.repository.specification.StudentSpecifications;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final UpdateStudentImageUseCase updateStudentImageUseCase;
    private final DeleteStudentImageUseCase deleteStudentImageUseCase;
    private final AddTeacherToFavoriteUseCase addTeacherToFavoriteUseCase;
    private final RemoveTeacherFromFavoriteUseCase removeTeacherFromFavoriteUseCase;

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

    @PostMapping("/{id}/deactivate")
    public Student deactivateStudent(@PathVariable Long id) {
        return deactivateStudentUseCase.execute(id);
    }

    @PostMapping("/{id}/activate")
    public Student activateStudent(@PathVariable Long id) {
        return activateStudentUseCase.execute(id);
    }

    @PostMapping("/{id}/favorite")
    public void addTeacherToFavorite(@PathVariable(name = "id") Long studentId, @RequestParam Long teacherId) {
        addTeacherToFavoriteUseCase.execute(studentId, teacherId);
    }

    @DeleteMapping("/{id}/favorite")
    public void removeTeacherFromFavorite(@PathVariable(name = "id") Long studentId, @RequestParam Long teacherId) {
        removeTeacherFromFavoriteUseCase.execute(studentId, teacherId);
    }

    @GetMapping("/{id}/favorite")
    public List<Teacher> listFavoriteTeachers(@PathVariable Long id) {
        return studentService.listFavoriteTeachers(id);
    }

    @PostMapping("/{id}/image")
    public byte[] updateImage(@PathVariable Long id, @RequestBody MultipartFile image) throws IOException {
        return updateStudentImageUseCase.execute(id, image);
    }

    @DeleteMapping("/{id}/image")
    public void deleteImage(@PathVariable Long id) {
        deleteStudentImageUseCase.execute(id);
    }
}
