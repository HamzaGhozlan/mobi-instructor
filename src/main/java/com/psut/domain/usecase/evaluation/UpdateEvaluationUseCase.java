package com.psut.domain.usecase.evaluation;

import com.psut.domain.validator.EvaluationValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.evaluation.Evaluation;
import com.psut.model.evaluation.UpdateEvaluationRequest;
import com.psut.service.EvaluationService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UpdateEvaluationUseCase {
    private final EvaluationValidator evaluationValidator;
    private final EvaluationService service;

    public Evaluation execute(Long id, UpdateEvaluationRequest updateRequest) {
        Evaluation evaluation = service.findById(id);
        updateRequest.applyUpdatesOn(evaluation);
        validate(evaluation);
        return service.update(evaluation);
    }

    private void validate(Evaluation evaluation) {
        Set<String> violations = evaluationValidator.validate(evaluation);
        if(!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
