package com.psut.model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEvaluationRequest {
    private int rate;
    private String review;

    public Evaluation applyUpdatesOn(Evaluation evaluation) {
        evaluation.setRate(rate);
        evaluation.setReview(review);
        return evaluation;
    }
}
