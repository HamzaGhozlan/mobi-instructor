package com.psut.repository.mapper;

import com.psut.model.Material;
import com.psut.repository.entity.MaterialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    @Mapping(target = "teacher", ignore = true)
    MaterialEntity toEntity(Material material);

    @Mapping(target = "teacher", ignore = true)
    Material toDomain(MaterialEntity materialEntity);
}
