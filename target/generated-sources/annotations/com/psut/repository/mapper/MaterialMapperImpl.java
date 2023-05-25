package com.psut.repository.mapper;

import com.psut.model.Material;
import com.psut.repository.entity.MaterialEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-23T19:48:04+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public MaterialEntity toEntity(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialEntity materialEntity = new MaterialEntity();

        materialEntity.setId( material.getId() );
        materialEntity.setCategory( material.getCategory() );
        materialEntity.setTitle( material.getTitle() );
        List<String> list = material.getStages();
        if ( list != null ) {
            materialEntity.setStages( new ArrayList<String>( list ) );
        }
        materialEntity.setPricePerHour( material.getPricePerHour() );
        materialEntity.setPricePerCourse( material.getPricePerCourse() );

        return materialEntity;
    }

    @Override
    public Material toDomain(MaterialEntity materialEntity) {
        if ( materialEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialEntity.getId() );
        material.setCategory( materialEntity.getCategory() );
        material.setTitle( materialEntity.getTitle() );
        List<String> list = materialEntity.getStages();
        if ( list != null ) {
            material.setStages( new ArrayList<String>( list ) );
        }
        material.setPricePerHour( materialEntity.getPricePerHour() );
        material.setPricePerCourse( materialEntity.getPricePerCourse() );

        return material;
    }
}
