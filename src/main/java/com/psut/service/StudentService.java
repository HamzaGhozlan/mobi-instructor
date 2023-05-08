package com.psut.service;

import com.psut.exception.RecordNotFoundException;
import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import com.psut.repository.JpaStudentRepository;
import com.psut.repository.entity.StudentEntity;
import com.psut.repository.entity.TeacherEntity;
import com.psut.repository.mapper.StudentMapper;
import com.psut.repository.mapper.TeacherMapper;
import com.psut.repository.specification.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final JpaStudentRepository jpaRepository;
    private final StudentMapper mapper;
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    public Student findById(Long id) {
        StudentEntity studentEntity = validateExistence(id);
        return mapper.toDomain(studentEntity);
    }

    public Student findByUsernameAndPassword(String username, String password) {
        StudentEntity student = jpaRepository.findByUsernameAndPassword(username, password);
        return mapper.toDomain(student);
    }

    public List<Student> findAll(Student example) {
        Example<StudentEntity> example1 = Example.of(mapper.toEntity(example));
        return mapper.toDomain(jpaRepository.findAll(example1));
    }

    public Page<Student> findAll(StudentSpecifications specifications, Pageable pageable) {
        return mapper.toDomain(jpaRepository.findAll(specifications, pageable));
    }

    @Transactional
    public Student save(Student student) {
        StudentEntity studentEntity = mapper.toEntity(student);
        studentEntity = jpaRepository.save(studentEntity);
        return mapper.toDomain(studentEntity);
    }

    @Transactional
    public Student update(Student student) {
        validateExistence(student.getId());
        StudentEntity studentEntity = mapper.toEntity(student);
        studentEntity = jpaRepository.save(studentEntity);
        return mapper.toDomain(studentEntity);
    }

    public void updateFavoriteTeachers(Long studentId, List<Long> teachersIds) {
        StudentEntity student = validateExistence(studentId);
        List<TeacherEntity> favoriteList = teacherService.listTeachers(teachersIds);
        student.setFavoriteTeachers(favoriteList);
        jpaRepository.save(student);
    }

    public List<Teacher> listFavoriteTeachers(Long studentId) {
        StudentEntity student = validateExistence(studentId);
        return teacherMapper.toDomain(student.getFavoriteTeachers());
    }

    public byte[] findImage(Long teacherId) {
        return validateExistence(teacherId).getImage();
    }

    public byte[] updateImage(Long studentId, byte[] image) {
        validateExistence(studentId);
        return jpaRepository.updateImage(studentId, image);
    }

    public void removeImage(Long studentId) {
        validateExistence(studentId);
        jpaRepository.updateImage(studentId, null);
    }

    public StudentEntity validateExistence(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
}
