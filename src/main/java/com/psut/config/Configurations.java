package com.psut.config;

import com.psut.domain.usecase.student.ActivateStudentUseCase;
import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.domain.usecase.student.DeactivateStudentUseCase;
import com.psut.domain.usecase.student.UpdateStudentUseCase;
import com.psut.domain.usecase.teacher.CreateTeacherUseCase;
import com.psut.domain.usecase.teacher.UpdateTeacherUseCase;
import com.psut.domain.validator.PasswordValidator;
import com.psut.domain.validator.StudentValidator;
import com.psut.domain.validator.TeacherValidator;
import com.psut.domain.validator.UsernameValidator;
import com.psut.repository.impl.StudentRepository;
import com.psut.repository.impl.TeacherRepository;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableJpaRepositories("com.psut.repository")
public class Configurations implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());
    }

    @Bean
    public CreateStudentUseCase createStudentUseCase(StudentRepository studentRepository,
                                                     StudentValidator studentValidator) {
        return new CreateStudentUseCase(studentValidator, studentRepository);
    }

    @Bean
    public UpdateStudentUseCase updateStudentUseCase(StudentRepository studentRepository,
                                                     StudentValidator studentValidator) {
        return new UpdateStudentUseCase(studentRepository, studentValidator);
    }

    @Bean
    public DeactivateStudentUseCase deactivateStudentUseCase(StudentRepository studentRepository) {
        return new DeactivateStudentUseCase(studentRepository);
    }

    @Bean
    public ActivateStudentUseCase activateStudentUseCase(StudentRepository studentRepository) {
        return new ActivateStudentUseCase(studentRepository);
    }

    @Bean
    public StudentValidator studentValidator(UsernameValidator usernameValidator,
                                             PasswordValidator passwordValidator) {
        return new StudentValidator(usernameValidator, passwordValidator);
    }

    @Bean
    public UsernameValidator usernameValidator(StudentRepository studentRepository,
                                               TeacherRepository teacherRepository) {
        return new UsernameValidator(studentRepository, teacherRepository);
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

    @Bean
    public CreateTeacherUseCase createTeacherUseCase(TeacherValidator teacherValidator,
                                                     TeacherRepository teacherRepository) {
        return new CreateTeacherUseCase(teacherValidator, teacherRepository);
    }

    @Bean
    public UpdateTeacherUseCase updateTeacherUseCase(TeacherValidator teacherValidator,
                                                     TeacherRepository teacherRepository){
        return new UpdateTeacherUseCase(teacherValidator, teacherRepository);
    }

    @Bean
    public TeacherValidator teachervalidator(UsernameValidator usernameValidator,
                                             PasswordValidator passwordValidator) {
        return new TeacherValidator(usernameValidator, passwordValidator);
    }
}
