package com.psut.domain.validator;

import com.psut.model.evaluation.Evaluation;

import java.util.Objects;
import java.util.Set;

public class EvaluationValidator extends AbstractValidator {
    public Set<String> validate(Evaluation evaluation) {
        if (Objects.isNull(evaluation)) {
            throw new IllegalArgumentException("evaluation.should.not.be.null");
        }

        validateNotNull(evaluation.getStudentId(), "student.id");
        validateNotNull(evaluation.getTeacherId(), "teacher.id");
        validateRate(evaluation.getRate());

        return violations;
    }

    private void validateRate(int rate) {
        if (rate <= 0 || rate >= 5) {
            violations.add("invalid.rate");
        }
    }
}
