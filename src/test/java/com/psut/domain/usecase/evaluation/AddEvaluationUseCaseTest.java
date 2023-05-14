package com.psut.domain.usecase.evaluation;

import com.psut.domain.validator.EvaluationValidator;
import com.psut.exception.BusinessValidationException;
import com.psut.model.evaluation.Evaluation;
import com.psut.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddEvaluationUseCaseTest {
    private static final Evaluation EVALUATION = new Evaluation();

    @Mock
    private EvaluationValidator evaluationValidator;
    @Mock
    private EvaluationService evaluationService;
    @InjectMocks
    private AddEvaluationUseCase useCase;

    @Test
    void givenValidEvaluationData_whenExecute_thenShouldInvokeSaveMethod() {
        when(evaluationValidator.validate(EVALUATION)).thenReturn(Collections.emptySet());
        when(evaluationService.evaluationExist(any(), any())).thenReturn(Boolean.FALSE);
        useCase.execute(EVALUATION);
        verify(evaluationService).save(EVALUATION);
    }

    @Test
    void givenInvalidEvaluationData_whenExecute_thenShouldThrowException() {
        when(evaluationValidator.validate(EVALUATION)).thenReturn(Collections.singleton("invalid"));
        when(evaluationService.evaluationExist(any(), any())).thenReturn(Boolean.FALSE);
        assertThrows(BusinessValidationException.class, () -> useCase.execute(EVALUATION));
    }

    @Test
    void givenExistEvaluation_whenExecute_thenShouldThrowException() {
        when(evaluationValidator.validate(EVALUATION)).thenReturn(new HashSet<>());
        when(evaluationService.evaluationExist(any(), any())).thenReturn(Boolean.TRUE);
        assertThrows(BusinessValidationException.class, () -> useCase.execute(EVALUATION));
    }
}