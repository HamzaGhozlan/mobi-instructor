package com.psut.domain.validator;

import com.psut.model.shared.User;
import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import com.psut.repository.impl.StudentRepository;
import com.psut.repository.impl.TeacherRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class UsernameValidator {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public void validate(User user, Set<String> violations) {
        if(usedFromStudent(user.getId(), user.getUsername()) || usedFromTeacher(user.getId(), user.getUsername())){
            violations.add("username.is.used");
        }
    }

    private boolean usedFromStudent(Long id, String username){
        Student example = new Student();
        example.setUsername(username);
        List<Student> similarStudent = studentRepository.findAll(example);

        return Objects.nonNull(similarStudent)
                && !similarStudent.isEmpty()
                && !similarStudent.get(0).getId().equals(id);
    }

    private boolean usedFromTeacher(Long id, String username){
        Teacher example = new Teacher();
        example.setUsername(username);
        List<Teacher> similarTeacher = teacherRepository.findAll(example);

        return Objects.nonNull(similarTeacher)
                && !similarTeacher.isEmpty()
                && !similarTeacher.get(0).getId().equals(id);
    }
}
