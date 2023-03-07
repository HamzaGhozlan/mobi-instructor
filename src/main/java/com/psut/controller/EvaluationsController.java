package com.psut.controller;

import com.psut.domain.usecase.teacher.AddEvaluationUseCase;
import com.psut.domain.usecase.teacher.DeleteEvaluationUseCase;
import com.psut.domain.usecase.teacher.UpdateEvaluationUseCase;
import com.psut.model.shared.Evaluation;
import com.psut.model.shared.UpdateEvaluationRequest;
import com.psut.repository.impl.EvaluationRepository;
import com.psut.repository.specification.EvaluationSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controller.EvaluationsController.EVALUATIONS_BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(EVALUATIONS_BASE_URL)
public class EvaluationsController {
    public static final String EVALUATIONS_BASE_URL = "api/v1/evaluations";

    private final EvaluationRepository evaluationRepository;
    private final AddEvaluationUseCase addEvaluationUseCase;
    private final UpdateEvaluationUseCase updateEvaluationUseCase;
    private final DeleteEvaluationUseCase deleteEvaluationUseCase;

    @GetMapping
    public List<Evaluation> listEvaluations(EvaluationSpecification specifications){
        return evaluationRepository.findAll(specifications);
    }

    @GetMapping("/{id}")
    public Evaluation getEvaluation(@PathVariable Long id) {
        return evaluationRepository.findById(id);
    }

    @PostMapping
    public Evaluation addEvaluation(@RequestBody Evaluation evaluation) {
        return addEvaluationUseCase.execute(evaluation);
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable Long id,
                                       @RequestBody UpdateEvaluationRequest updateRequest) {
        Evaluation evaluation = evaluationRepository.findById(id);
        return updateEvaluationUseCase.execute(evaluation, updateRequest);
    }

    @DeleteMapping("/{id}")
    public void removeEvaluation(@PathVariable Long id) {
        Evaluation evaluation = evaluationRepository.findById(id);
        deleteEvaluationUseCase.execute(evaluation);
    }
}
