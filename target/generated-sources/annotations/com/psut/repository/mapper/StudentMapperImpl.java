package com.psut.repository.mapper;

import com.psut.model.student.Student;
import com.psut.repository.entity.StudentEntity;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-23T19:48:04+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentEntity toEntity(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( student.getId() );
        studentEntity.setFirstName( student.getFirstName() );
        studentEntity.setLastName( student.getLastName() );
        studentEntity.setGender( student.getGender() );
        studentEntity.setUsername( student.getUsername() );
        studentEntity.setPassword( student.getPassword() );
        studentEntity.setStatus( student.getStatus() );
        studentEntity.setEmail( student.getEmail() );
        byte[] image = student.getImage();
        if ( image != null ) {
            studentEntity.setImage( Arrays.copyOf( image, image.length ) );
        }

        return studentEntity;
    }

    @Override
    public Student toDomain(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentEntity.getId() );
        student.setFirstName( studentEntity.getFirstName() );
        student.setLastName( studentEntity.getLastName() );
        student.setGender( studentEntity.getGender() );
        student.setUsername( studentEntity.getUsername() );
        student.setPassword( studentEntity.getPassword() );
        student.setStatus( studentEntity.getStatus() );
        student.setEmail( studentEntity.getEmail() );
        byte[] image = studentEntity.getImage();
        if ( image != null ) {
            student.setImage( Arrays.copyOf( image, image.length ) );
        }

        return student;
    }
}
