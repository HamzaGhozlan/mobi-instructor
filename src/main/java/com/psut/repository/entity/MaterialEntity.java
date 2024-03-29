package com.psut.repository.entity;

import com.psut.model.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    @ElementCollection
    @CollectionTable(name = "material_stages", joinColumns = @JoinColumn(name = "material_id"))
    @Column(name = "stage")
    private List<String> stages;
    @Column(name = "price_per_hour")
    private BigDecimal pricePerHour;
    @Column(name = "price_per_course")
    private BigDecimal pricePerCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
}
