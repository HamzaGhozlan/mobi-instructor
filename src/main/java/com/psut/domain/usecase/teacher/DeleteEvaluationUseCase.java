package com.psut.domain.usecase.teacher;

import com.psut.model.shared.Evaluation;
import com.psut.repository.impl.EvaluationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteEvaluationUseCase {
    private final EvaluationRepository repository;

    public void execute(Evaluation evaluation) {
        repository.delete(evaluation);
    }
}
