package com.psut.repository.mapper;

import com.psut.model.Material;
import com.psut.repository.entity.MaterialEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TeacherMapper.class)
public interface MaterialMapper {
    MaterialEntity toEntity(Material material);

    Material toDomain(MaterialEntity materialEntity);
}
