package com.psut.model.shared;

import lombok.Data;

@Data
public class Evaluation {
    private Long id;
    private int rate;
    private String review;
    private Long teacherId;
    private Long studentId;
}
