package com.psut.configs;

import com.psut.repositories.StudentRepository;
import com.psut.usecases.student.ActivateStudentUseCase;
import com.psut.usecases.student.CreateStudentUseCase;
import com.psut.usecases.student.DeactivateStudentUseCase;
import com.psut.usecases.student.UpdateStudentUseCase;
import com.psut.validators.PasswordValidator;
import com.psut.validators.StudentValidator;
import com.psut.validators.UsernameValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {
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
    public UsernameValidator usernameValidator() {
        return new UsernameValidator();
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }
}
