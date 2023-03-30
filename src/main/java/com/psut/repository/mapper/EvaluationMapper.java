package com.psut.repository.mapper;

import com.psut.model.evaluation.Evaluation;
import com.psut.repository.entity.EvaluationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    @Mappings({
            @Mapping(source = "teacherId", target = "teacher.id"),
            @Mapping(source = "studentId", target = "student.id")
    })
    EvaluationEntity toEntity(Evaluation evaluation);

    @Mappings({
            @Mapping(source = "teacher.id", target = "teacherId"),
            @Mapping(source = "student.id", target = "studentId")
    })
    Evaluation toDomain(EvaluationEntity evaluationEntity);

    default Page<Evaluation> toDomain(Page<EvaluationEntity> evaluationEntities) {
        return evaluationEntities.map(this::toDomain);
    }
}
