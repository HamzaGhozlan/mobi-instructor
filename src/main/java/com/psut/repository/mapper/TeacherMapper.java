package com.psut.repository.mapper;

import com.psut.model.teacher.Teacher;
import com.psut.repository.entity.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MaterialMapper.class)
public interface TeacherMapper {
    TeacherEntity toEntity(Teacher teacher);

    Teacher toDomain(TeacherEntity teacherEntity);
}
