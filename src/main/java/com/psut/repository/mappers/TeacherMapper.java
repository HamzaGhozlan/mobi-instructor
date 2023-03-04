package com.psut.repository.mappers;

import com.psut.model.teacher.Teacher;
import com.psut.repository.entities.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherEntity toEntity(Teacher teacher);

    Teacher toDomain(TeacherEntity teacherEntity);
}
