package com.psut.controller;

import com.psut.domain.usecase.teacher.CreateTeacherUseCase;
import com.psut.domain.usecase.teacher.UpdateTeacherUseCase;
import com.psut.model.teacher.Teacher;
import com.psut.repository.impl.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controller.TeachersController.TEACHERS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(TEACHERS_BASE_URL)
public class TeachersController {
    public static final String TEACHERS_BASE_URL = "/api/v1/teachers";

    private final TeacherRepository teacherRepository;
    private final CreateTeacherUseCase createTeacherUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;

    @GetMapping
    public List<Teacher> listTeachers() {
        return null;
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return null;
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable String id) {
        return null;
    }
}
