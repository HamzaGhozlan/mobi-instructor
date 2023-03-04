package com.psut.model.shared;

import com.psut.model.teacher.Teacher;
import lombok.Data;

@Data
public class Evaluation {
    private Double rate;
    private String review;
    private Teacher teacher;
}
