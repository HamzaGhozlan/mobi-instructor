package com.psut.domain.usecase.evaluation;

import com.psut.domain.validator.EvaluationValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.evaluation.Evaluation;
import com.psut.model.evaluation.UpdateEvaluationRequest;
import com.psut.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateEvaluationUseCaseTest {
    private static final Evaluation EVALUATION = new Evaluation();

    @Mock
    private EvaluationValidator evaluationValidator;
    @Mock
    private EvaluationService evaluationService;
    @InjectMocks
    private UpdateEvaluationUseCase useCase;

    @Test
    void givenValidUpdateRequest_whenExecute_thenShouldInvokeUpdateMethod() {
        Evaluation oldEvaluation = getEvaluation();
        UpdateEvaluationRequest updateRequest = getUpdateRequest();
        Evaluation updatedEvaluation = updateRequest.applyUpdatesOn(getEvaluation());

        when(evaluationService.findById(1L)).thenReturn(oldEvaluation);
        when(evaluationValidator.validate(updatedEvaluation)).thenReturn(Collections.emptySet());
        useCase.execute(1L, updateRequest);

        verify(evaluationService).update(updatedEvaluation);
    }

    @Test
    void givenInvalidUpdateRequest_whenExecute_thenShouldThrowException() {
        when(evaluationService.findById(1L)).thenReturn(new Evaluation());
        when(evaluationValidator.validate(any())).thenReturn(Collections.singleton("invalid"));
        assertThrows(BusinessValidationException.class,
                () -> useCase.execute(1L, new UpdateEvaluationRequest())
        );
    }

    private Evaluation getEvaluation() {
        return Evaluation.builder()
                .id(1L)
                .rate(1)
                .review("reviewMsg")
                .build();
    }

    private UpdateEvaluationRequest getUpdateRequest(){
        return UpdateEvaluationRequest.builder()
                .review("updateMsg")
                .rate(5)
                .build();
    }
}