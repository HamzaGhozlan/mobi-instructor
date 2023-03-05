package com.psut.domain.validator;

import com.psut.model.shared.Gender;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.TeachingWay;
import com.psut.repository.impl.TeacherRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class TeacherValidator extends AbstractValidator{
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;

    public Set<String> validate(Teacher teacher) {
        violations = new HashSet();

        validateNotNull(teacher.getGender(), "gender");
        validateNotBlank(teacher.getFirstName(), "first.name");
        validateNotBlank(teacher.getLastName(), "last.name");
        validateNotBlank(teacher.getCity(), "city");
        validateNotBlank(teacher.getPhoneNumber(), "phone.number");
        validateEmail(teacher.getEmail());
        validateTeachingWays(teacher.getTeachingWay());
        validateTargetedStudents(teacher.getTargetedStudents());
        usernameValidator.validate(teacher, violations);
        passwordValidator.validate(teacher.getPassword(), violations);
        return violations;
    }

    private void validateEmail(String email) {
        if(StringUtils.isBlank(email) || !email.contains("@")){
            violations.add("invalid.email");
        }
    }

    private void validateTeachingWays(List<TeachingWay> teachingWays){
        if(Objects.isNull(teachingWays) || teachingWays.isEmpty()){
            violations.add("invalid.teaching.way");
        }
    }

    private void validateTargetedStudents(List<Gender> targetedStudents){
        if(Objects.isNull(targetedStudents) || targetedStudents.isEmpty()){
            violations.add("invalid.targeted.students");
        }
    }
}
