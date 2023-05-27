package com.psut.model.teacher;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Material {
    private Long id;
    private Category category;
    private String title;
    private List<String> stages;
    private BigDecimal pricePerHour;
    private BigDecimal pricePerCourse;

    private Teacher teacher;
}
