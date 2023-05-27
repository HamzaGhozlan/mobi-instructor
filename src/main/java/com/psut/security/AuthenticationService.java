package com.psut.security;

import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.domain.usecase.teacher.CreateTeacherUseCase;
import com.psut.model.shared.UserRole;
import com.psut.model.student.Student;
import com.psut.model.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CreateTeacherUseCase createTeacherUseCase;
    private final CreateStudentUseCase createStudentUseCase;
    private final JwtService jwtService;

    public String registerTeacher(Teacher teacher) {
        teacher.setUserRole(UserRole.TEACHER);
        teacher = createTeacherUseCase.execute(teacher);
        return jwtService.generateToken(teacher);
    }

    public String registerStudent(Student student) {
        student.setUserRole(UserRole.STUDENT);
        student = createStudentUseCase.execute(student);
        return jwtService.generateToken(student);
    }


}
