package com.psut.domain.usecase.evaluation;

import com.psut.model.evaluation.Evaluation;
import com.psut.service.EvaluationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteEvaluationUseCase {
    private final EvaluationService service;

    public void execute(Long id) {
        Evaluation evaluation = service.findById(id);
        service.delete(evaluation);
    }
}
