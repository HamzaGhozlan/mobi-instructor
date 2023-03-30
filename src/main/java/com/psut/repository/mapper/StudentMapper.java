package com.psut.repository.mapper;

import com.psut.model.student.Student;
import com.psut.repository.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(Student student);

    Student toDomain(StudentEntity studentEntity);

    default Page<Student> toDomain(Page<StudentEntity> studentEntities) {
        return studentEntities.map(this::toDomain);
    }

    default List<Student> toDomain(List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
