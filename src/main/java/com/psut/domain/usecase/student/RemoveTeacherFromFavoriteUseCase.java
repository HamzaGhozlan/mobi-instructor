package com.psut.domain.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RemoveTeacherFromFavoriteUseCase {
    private final StudentService studentService;

    public void execute(Long studentId, Long teacherId) {
        List<Long> favoriteList = studentService
                .listFavoriteTeachers(studentId)
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());

        boolean wasExist = favoriteList.remove(teacherId);
        if(!wasExist) {
            throw new TechnicalValidationException("teacher.does.not.in.favorite.list");
        }

        studentService.updateFavoriteTeachers(studentId, favoriteList);
    }
}
