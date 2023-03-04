package com.psut.config;

import com.psut.repository.impl.StudentRepository;
import com.psut.domain.usecase.student.ActivateStudentUseCase;
import com.psut.domain.usecase.student.CreateStudentUseCase;
import com.psut.domain.usecase.student.DeactivateStudentUseCase;
import com.psut.domain.usecase.student.UpdateStudentUseCase;
import com.psut.domain.validator.PasswordValidator;
import com.psut.domain.validator.StudentValidator;
import com.psut.domain.validator.UsernameValidator;
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
    public StudentValidator studentValidator(PasswordValidator passwordValidator,
                                             UsernameValidator usernameValidator) {
        return new StudentValidator(passwordValidator, usernameValidator);
    }

    @Bean
    public UsernameValidator usernameValidator(StudentRepository studentRepository) {
        return new UsernameValidator(studentRepository);
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

}
