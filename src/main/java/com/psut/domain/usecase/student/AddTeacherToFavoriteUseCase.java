package com.psut.domain.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddTeacherToFavoriteUseCase {
    private final StudentService studentService;

    public void execute(Long studentId, Long teacherId) {
        List<Long> favoriteList = studentService
                .listFavoriteTeachers(studentId)
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());

        if (favoriteList.contains(teacherId)) {
            throw new TechnicalValidationException("teacher.is.in.the.favorite.list");
        }

        favoriteList.add(teacherId);
        studentService.updateFavoriteTeachers(studentId, favoriteList);
    }
}
