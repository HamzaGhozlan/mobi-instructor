package com.psut.domain.validator;

import com.psut.model.evaluation.Evaluation;

import java.util.Collections;
import java.util.Set;

public class EvaluationValidator {
    public Set<String> validate(Evaluation evaluation) {
        if (evaluation.getRate() <= 0 || evaluation.getRate() > 5) {
            return Collections.singleton("invalid.rate");
        }
        return Collections.emptySet();
    }
}
