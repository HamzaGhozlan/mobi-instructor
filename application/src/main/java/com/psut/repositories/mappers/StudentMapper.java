package com.psut.repositories.mappers;

import com.psut.models.student.Student;
import com.psut.repositories.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(Student student);
    Student toDomain(StudentEntity studentEntity);
}
