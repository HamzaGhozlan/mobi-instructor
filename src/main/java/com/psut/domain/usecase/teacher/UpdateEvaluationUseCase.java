package com.psut.domain.usecase.teacher;

import com.psut.domain.validator.EvaluationValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.shared.Evaluation;
import com.psut.model.shared.UpdateEvaluationRequest;
import com.psut.repository.impl.EvaluationRepository;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UpdateEvaluationUseCase {
    private final EvaluationValidator evaluationValidator;
    private final EvaluationRepository repository;

    public Evaluation execute(Evaluation evaluation, UpdateEvaluationRequest updateRequest) {
        updateRequest.applyUpdatesOn(evaluation);
        validate(evaluation);
        return repository.update(evaluation);
    }

    private void validate(Evaluation evaluation) {
        Set<String> violations = evaluationValidator.validate(evaluation);
        if(!violations.isEmpty()) {
            throw new BusinessValidationException(violations);
        }
    }
}
