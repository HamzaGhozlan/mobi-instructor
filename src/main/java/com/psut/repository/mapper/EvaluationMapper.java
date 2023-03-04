package com.psut.repository.mapper;

import com.psut.model.shared.Evaluation;
import com.psut.repository.entity.EvaluationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    EvaluationEntity toEntity(Evaluation evaluation);

    Evaluation toDomain(EvaluationEntity evaluationEntity);
}
