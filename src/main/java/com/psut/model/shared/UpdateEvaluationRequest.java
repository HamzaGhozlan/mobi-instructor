package com.psut.model.shared;

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
