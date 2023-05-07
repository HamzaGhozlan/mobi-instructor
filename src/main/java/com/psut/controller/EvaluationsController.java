package com.psut.controller;

import com.psut.domain.usecase.evaluation.AddEvaluationUseCase;
import com.psut.domain.usecase.evaluation.UpdateEvaluationUseCase;
import com.psut.model.evaluation.Evaluation;
import com.psut.model.evaluation.UpdateEvaluationRequest;
import com.psut.repository.specification.EvaluationSpecifications;
import com.psut.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.psut.controller.EvaluationsController.EVALUATIONS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(EVALUATIONS_BASE_URL)
public class EvaluationsController {
    public static final String EVALUATIONS_BASE_URL = "/api/v1/evaluations";

    private final EvaluationService evaluationService;
    private final AddEvaluationUseCase addEvaluationUseCase;
    private final UpdateEvaluationUseCase updateEvaluationUseCase;

    @GetMapping
    public Page<Evaluation> listEvaluations(EvaluationSpecifications specifications, Pageable pageable) {
        return evaluationService.findAll(specifications, pageable);
    }

    @GetMapping("/{id}")
    public Evaluation getEvaluation(@PathVariable Long id) {
        return evaluationService.findById(id);
    }

    @PostMapping
    public Evaluation addEvaluation(@RequestBody Evaluation evaluation) {
        return addEvaluationUseCase.execute(evaluation);
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable Long id,
                                       @RequestBody UpdateEvaluationRequest updateRequest) {
        return updateEvaluationUseCase.execute(id, updateRequest);
    }

    @DeleteMapping("/{id}")
    public void removeEvaluation(@PathVariable Long id) {
        evaluationService.delete(id);
    }
}
