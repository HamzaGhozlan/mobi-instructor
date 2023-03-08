package com.psut.controller;

import com.psut.domain.usecase.teacher.CreateTeacherUseCase;
import com.psut.domain.usecase.teacher.UpdateTeacherUseCase;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.UpdateTeacherRequest;
import com.psut.service.TeacherService;
import com.psut.repository.specification.TeacherSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controller.TeachersController.TEACHERS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(TEACHERS_BASE_URL)
public class TeachersController {
    public static final String TEACHERS_BASE_URL = "/api/v1/teachers";

    private final TeacherService teacherService;
    private final CreateTeacherUseCase createTeacherUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;

    @GetMapping
    public List<Teacher> listTeachers(TeacherSpecifications specifications) {
        return teacherService.findAll(specifications);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return createTeacherUseCase.execute(teacher);
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherRequest updateRequest) {
        return updateTeacherUseCase.execute(teacherService.findById(id), updateRequest);
    }

}
