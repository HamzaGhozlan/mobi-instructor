package com.psut.repository.specification;

import com.psut.repository.entity.EvaluationEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(params = "teacherId", path = "teacher.id", spec = Equal.class),
        @Spec(params = "rate", path = "rate", spec = Equal.class),
        @Spec(params = "studentId", path = "student.id", spec = Equal.class)
})
public interface EvaluationSpecifications extends Specification<EvaluationEntity> {
}
