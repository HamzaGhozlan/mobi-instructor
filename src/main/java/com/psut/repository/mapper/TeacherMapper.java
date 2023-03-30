package com.psut.repository.mapper;

import com.psut.model.teacher.Teacher;
import com.psut.repository.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = MaterialMapper.class)
public interface TeacherMapper {
    TeacherEntity toEntity(Teacher teacher);

    Teacher toDomain(TeacherEntity teacherEntity);

    default Page<Teacher> toDomain(Page<TeacherEntity> teacherEntities) {
        return teacherEntities.map(this::toDomain);
    }

    default List<Teacher> toDomain(List<TeacherEntity> teacherEntities) {
        return teacherEntities.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
