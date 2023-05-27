package com.psut.config;

import com.psut.domain.usecase.evaluation.AddEvaluationUseCase;
import com.psut.domain.usecase.evaluation.UpdateEvaluationUseCase;
import com.psut.domain.usecase.student.*;
import com.psut.domain.usecase.teacher.*;
import com.psut.domain.validator.*;
import com.psut.service.EvaluationService;
import com.psut.service.StudentService;
import com.psut.service.TeacherService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableJpaRepositories("com.psut.repository")
@RequiredArgsConstructor
public class Configurations implements WebMvcConfigurer {
    private final StudentService studentService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return studentService::findByUsername;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CreateStudentUseCase createStudentUseCase(StudentService studentService,
                                                     StudentValidator studentValidator,
                                                     PasswordEncoder passwordEncoder) {
        return new CreateStudentUseCase(studentValidator, studentService, passwordEncoder);
    }

    @Bean
    public UpdateStudentUseCase updateStudentUseCase(StudentService studentService,
                                                     StudentValidator studentValidator) {
        return new UpdateStudentUseCase(studentService, studentValidator);
    }

    @Bean
    public DeactivateStudentUseCase deactivateStudentUseCase(StudentService studentService) {
        return new DeactivateStudentUseCase(studentService);
    }

    @Bean
    public ActivateStudentUseCase activateStudentUseCase(StudentService studentService) {
        return new ActivateStudentUseCase(studentService);
    }

    @Bean
    public StudentValidator studentValidator(UsernameValidator usernameValidator,
                                             PasswordValidator passwordValidator) {
        return new StudentValidator(usernameValidator, passwordValidator);
    }

    @Bean
    public UsernameValidator usernameValidator(StudentService studentService,
                                               TeacherService teacherService) {
        return new UsernameValidator(studentService, teacherService);
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

    @Bean
    public ImageValidator imageValidator() {
        return new ImageValidator();
    }

    @Bean
    public CreateTeacherUseCase createTeacherUseCase(TeacherValidator teacherValidator,
                                                     TeacherService teacherService,
                                                     PasswordEncoder passwordEncoder) {
        return new CreateTeacherUseCase(teacherValidator, teacherService, passwordEncoder);
    }

    @Bean
    public UpdateTeacherUseCase updateTeacherUseCase(TeacherValidator teacherValidator,
                                                     TeacherService teacherService) {
        return new UpdateTeacherUseCase(teacherValidator, teacherService);
    }

    @Bean
    public TeacherValidator teachervalidator(UsernameValidator usernameValidator,
                                             PasswordValidator passwordValidator) {
        return new TeacherValidator(usernameValidator, passwordValidator);
    }

    @Bean
    public AddEvaluationUseCase addEvaluationUseCase(EvaluationValidator evaluationValidator,
                                                     EvaluationService evaluationService) {
        return new AddEvaluationUseCase(evaluationValidator, evaluationService);
    }

    @Bean
    public UpdateEvaluationUseCase updateEvaluationUseCase(EvaluationValidator evaluationValidator,
                                                           EvaluationService evaluationService) {
        return new UpdateEvaluationUseCase(evaluationValidator, evaluationService);
    }

    @Bean
    public EvaluationValidator evaluationValidator() {
        return new EvaluationValidator();
    }

    @Bean
    public UpdateStudentImageUseCase updateStudentImageUseCase(StudentService studentService,
                                                               ImageValidator imageValidator) {
        return new UpdateStudentImageUseCase(studentService, imageValidator);
    }

    @Bean
    public DeleteStudentImageUseCase deleteStudentImageUseCase(StudentService studentService) {
        return new DeleteStudentImageUseCase(studentService);
    }

    @Bean
    public UpdateTeacherImageUseCase updateTeacherImageUseCase(TeacherService teacherService,
                                                               ImageValidator imageValidator) {
        return new UpdateTeacherImageUseCase(teacherService, imageValidator);
    }

    @Bean
    public DeleteTeacherImageUseCase deleteTeacherImageUseCase(TeacherService teacherService) {
        return new DeleteTeacherImageUseCase(teacherService);
    }

    @Bean
    public AddTeacherToFavoriteUseCase addTeacherToFavoriteUseCase(StudentService studentService) {
        return new AddTeacherToFavoriteUseCase(studentService);
    }

    @Bean
    public RemoveTeacherFromFavoriteUseCase removeTeacherFromFavoriteUseCase(StudentService studentService) {
        return new RemoveTeacherFromFavoriteUseCase(studentService);
    }

    @Bean
    public ActivateTeacherUseCase activateTeacherUseCase(TeacherService teacherService) {
        return new ActivateTeacherUseCase(teacherService);
    }

    @Bean
    public DeactivateTeacherUseCase deactivateTeacherUseCase(TeacherService teacherService) {
        return new DeactivateTeacherUseCase(teacherService);
    }

}
