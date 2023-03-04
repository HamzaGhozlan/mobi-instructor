package com.psut.repository.mapper;

import com.psut.model.student.Student;
import com.psut.repository.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(Student student);

    Student toDomain(StudentEntity studentEntity);
}
