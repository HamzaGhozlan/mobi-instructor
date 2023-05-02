package com.psut.domain.usecase.evaluation;

import com.psut.domain.validator.EvaluationValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.evaluation.Evaluation;
import com.psut.service.EvaluationService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class AddEvaluationUseCase {
    private final EvaluationValidator evaluationValidator;
    private final EvaluationService service;

    public Evaluation execute(Evaluation evaluation) {
        validate(evaluation);
        return service.save(evaluation);
    }

    private void validate(Evaluation evaluation) {
        Set<String> violations = evaluationValidator.validate(evaluation);

        boolean exist = service.evaluationExist(evaluation.getTeacherId(), evaluation.getStudentId());
        if (exist) {
            violations.add("student.already.evaluated.this.teacher");
        }

        if (!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
