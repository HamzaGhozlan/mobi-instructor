package com.psut.repository.mappers;

import com.psut.model.student.Student;
import com.psut.repository.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(Student student);

    Student toDomain(StudentEntity studentEntity);
}
