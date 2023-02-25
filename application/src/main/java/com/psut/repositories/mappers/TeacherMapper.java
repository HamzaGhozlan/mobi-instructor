package com.psut.repositories.mappers;

import com.psut.models.teacher.Teacher;
import com.psut.repositories.entities.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherEntity toEntity(Teacher teacher);
    Teacher toDomain(TeacherEntity teacherEntity);
}
