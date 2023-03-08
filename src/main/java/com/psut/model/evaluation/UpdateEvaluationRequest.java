package com.psut.model.evaluation;

import com.psut.model.evaluation.Evaluation;
import lombok.Data;

@Data
public class UpdateEvaluationRequest {
    private int rate;
    private String review;

    public void applyUpdatesOn(Evaluation evaluation) {
        evaluation.setRate(rate);
        evaluation.setReview(review);
    }
}
