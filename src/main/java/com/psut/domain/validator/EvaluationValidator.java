package com.psut.domain.validator;

import com.psut.model.evaluation.Evaluation;

import java.util.Collections;
import java.util.Set;

public class EvaluationValidator {
    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 5;

    public Set<String> validate(Evaluation evaluation) {
        if (evaluation.getRate() < MIN_RATE || evaluation.getRate() > MAX_RATE) {
            return Collections.singleton("invalid.rate");
        }
        return Collections.emptySet();
    }
}
