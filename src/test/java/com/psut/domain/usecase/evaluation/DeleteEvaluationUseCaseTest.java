package com.psut.domain.usecase.evaluation;

import com.psut.model.evaluation.Evaluation;
import com.psut.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteEvaluationUseCaseTest {
    private static final Evaluation EVALUATION = new Evaluation();

    @Mock
    private EvaluationService evaluationService;
    @InjectMocks
    private DeleteEvaluationUseCase useCase;

    @Test
    void whenExecute_thenShouldInvokeDeletionMethod() {
        when(evaluationService.findById(1L)).thenReturn(EVALUATION);
        useCase.execute(1L);
        verify(evaluationService).delete(EVALUATION);
    }
}