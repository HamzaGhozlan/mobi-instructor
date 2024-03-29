package com.psut.model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private Long id;
    private int rate;
    private String review;
    private Long teacherId;
    private Long studentId;
}
