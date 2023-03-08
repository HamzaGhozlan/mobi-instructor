package com.psut.domain.validator;

import com.psut.model.shared.User;
import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class UsernameValidator {
    private final StudentService studentService;
    private final TeacherService teacherService;

    public void validate(User user, Set<String> violations) {
        if(usedFromStudent(user.getId(), user.getUsername()) || usedFromTeacher(user.getId(), user.getUsername())){
            violations.add("username.is.used");
        }
    }

    private boolean usedFromStudent(Long id, String username){
        Student example = new Student();
        example.setUsername(username);
        List<Student> similarStudent = studentService.findAll(example);

        return Objects.nonNull(similarStudent)
                && !similarStudent.isEmpty()
                && !similarStudent.get(0).getId().equals(id);
    }

    private boolean usedFromTeacher(Long id, String username){
        Teacher example = new Teacher();
        example.setUsername(username);
        List<Teacher> similarTeacher = teacherService.findAll(example);

        return Objects.nonNull(similarTeacher)
                && !similarTeacher.isEmpty()
                && !similarTeacher.get(0).getId().equals(id);
    }
}
