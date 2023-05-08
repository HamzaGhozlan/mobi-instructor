package com.psut.controller;

import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.psut.controller.LoginController.LOGIN_BASE_URL;

@RequiredArgsConstructor
@RequestMapping(LOGIN_BASE_URL)
@RestController
public class LoginController {
    public static final String LOGIN_BASE_URL = "/api/v1/login";
    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("/student")
    public ResponseEntity<Student> studentLogin(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.findByUsernameAndPassword(username, password);
        return ResponseEntity.status(getStatus(student)).body(student);
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> teacherLogin(@RequestParam String username, @RequestParam String password) {
        Teacher teacher = teacherService.findByUsernameAndPassword(username, password);
        return ResponseEntity.status(getStatus(teacher)).body(teacher);
    }

    private HttpStatus getStatus(Object obj) {
        return Objects.nonNull(obj) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
