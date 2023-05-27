package com.psut.security;

import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final StudentService studentService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register/student")
    public ResponseEntity<String> register(@RequestBody Student student) {
        return ResponseEntity.ok(
                authenticationService.registerStudent(student)
        );
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<String> register(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(
                authenticationService.registerTeacher(teacher)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        Student student = studentService.findByUsername(username);
        return ResponseEntity.ok(
                jwtService.generateToken(student)
        );
    }
}
