package com.psut.controller;

import com.psut.domain.usecase.evaluation.AddEvaluationUseCase;
import com.psut.domain.usecase.evaluation.DeleteEvaluationUseCase;
import com.psut.domain.usecase.evaluation.UpdateEvaluationUseCase;
import com.psut.model.evaluation.Evaluation;
import com.psut.model.evaluation.UpdateEvaluationRequest;
import com.psut.service.EvaluationService;
import com.psut.repository.specification.EvaluationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controller.EvaluationsController.EVALUATIONS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(EVALUATIONS_BASE_URL)
public class EvaluationsController {
    public static final String EVALUATIONS_BASE_URL = "api/v1/evaluations";

    private final EvaluationService evaluationService;
    private final AddEvaluationUseCase addEvaluationUseCase;
    private final UpdateEvaluationUseCase updateEvaluationUseCase;
    private final DeleteEvaluationUseCase deleteEvaluationUseCase;

    @GetMapping
    public List<Evaluation> listEvaluations(EvaluationSpecifications specifications, Sort sort){
        return evaluationService.findAll(specifications, sort);
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
        Evaluation evaluation = evaluationService.findById(id);
        return updateEvaluationUseCase.execute(evaluation, updateRequest);
    }

    @DeleteMapping("/{id}")
    public void removeEvaluation(@PathVariable Long id) {
        Evaluation evaluation = evaluationService.findById(id);
        deleteEvaluationUseCase.execute(evaluation);
    }
}
