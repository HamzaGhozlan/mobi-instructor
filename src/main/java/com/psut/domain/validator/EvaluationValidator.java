package com.psut.domain.validator;

import com.psut.model.evaluation.Evaluation;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class EvaluationValidator {
    private static final double MIN_RATE = 1.0;
    private static final double MAX_RATE = 5.0;

    public Set<String> validate(Evaluation evaluation) {
        Set<String> violations = new HashSet<>();
        validateRate(evaluation, violations);
        return violations;
    }

    private void validateRate(Evaluation evaluation, Set<String> violations) {
        if (evaluation.getRate() < MIN_RATE || evaluation.getRate() > MAX_RATE) {
            violations.add("invalid.rate");
        }
    }
}
