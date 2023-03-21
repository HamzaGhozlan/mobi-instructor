package com.psut.domain.validator;

import com.psut.model.evaluation.Evaluation;

import java.util.Set;

public class EvaluationValidator extends AbstractValidator {
    public Set<String> validate(Evaluation evaluation) {
        validateRate(evaluation.getRate());
        return violations;
    }

    private void validateRate(int rate) {
        if (rate <= 0 || rate > 5) {
            violations.add("invalid.rate");
        }
    }
}
