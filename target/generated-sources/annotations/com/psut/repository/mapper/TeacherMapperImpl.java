package com.psut.repository.mapper;

import com.psut.model.Material;
import com.psut.model.shared.Gender;
import com.psut.model.teacher.Teacher;
import com.psut.model.teacher.TeachingWay;
import com.psut.repository.entity.MaterialEntity;
import com.psut.repository.entity.TeacherEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-23T19:48:03+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public TeacherEntity toEntity(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherEntity teacherEntity = new TeacherEntity();

        teacherEntity.setId( teacher.getId() );
        teacherEntity.setFirstName( teacher.getFirstName() );
        teacherEntity.setLastName( teacher.getLastName() );
        teacherEntity.setGender( teacher.getGender() );
        teacherEntity.setUsername( teacher.getUsername() );
        teacherEntity.setPassword( teacher.getPassword() );
        teacherEntity.setStatus( teacher.getStatus() );
        teacherEntity.setEmail( teacher.getEmail() );
        byte[] image = teacher.getImage();
        if ( image != null ) {
            teacherEntity.setImage( Arrays.copyOf( image, image.length ) );
        }
        teacherEntity.setDescription( teacher.getDescription() );
        teacherEntity.setPhoneNumber( teacher.getPhoneNumber() );
        teacherEntity.setCity( teacher.getCity() );
        List<Gender> list = teacher.getTargetedStudents();
        if ( list != null ) {
            teacherEntity.setTargetedStudents( new ArrayList<Gender>( list ) );
        }
        List<TeachingWay> list1 = teacher.getTeachingWays();
        if ( list1 != null ) {
            teacherEntity.setTeachingWays( new ArrayList<TeachingWay>( list1 ) );
        }
        teacherEntity.setRateAvg( teacher.getRateAvg() );
        teacherEntity.setMaterials( materialListToMaterialEntityList( teacher.getMaterials() ) );

        return teacherEntity;
    }

    @Override
    public Teacher toDomain(TeacherEntity teacherEntity) {
        if ( teacherEntity == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( teacherEntity.getId() );
        teacher.setFirstName( teacherEntity.getFirstName() );
        teacher.setLastName( teacherEntity.getLastName() );
        teacher.setGender( teacherEntity.getGender() );
        teacher.setUsername( teacherEntity.getUsername() );
        teacher.setPassword( teacherEntity.getPassword() );
        teacher.setStatus( teacherEntity.getStatus() );
        teacher.setEmail( teacherEntity.getEmail() );
        byte[] image = teacherEntity.getImage();
        if ( image != null ) {
            teacher.setImage( Arrays.copyOf( image, image.length ) );
        }
        teacher.setDescription( teacherEntity.getDescription() );
        teacher.setPhoneNumber( teacherEntity.getPhoneNumber() );
        teacher.setCity( teacherEntity.getCity() );
        List<Gender> list = teacherEntity.getTargetedStudents();
        if ( list != null ) {
            teacher.setTargetedStudents( new ArrayList<Gender>( list ) );
        }
        List<TeachingWay> list1 = teacherEntity.getTeachingWays();
        if ( list1 != null ) {
            teacher.setTeachingWays( new ArrayList<TeachingWay>( list1 ) );
        }
        teacher.setRateAvg( teacherEntity.getRateAvg() );
        teacher.setMaterials( materialEntityListToMaterialList( teacherEntity.getMaterials() ) );

        return teacher;
    }

    protected List<MaterialEntity> materialListToMaterialEntityList(List<Material> list) {
        if ( list == null ) {
            return null;
        }

        List<MaterialEntity> list1 = new ArrayList<MaterialEntity>( list.size() );
        for ( Material material : list ) {
            list1.add( materialMapper.toEntity( material ) );
        }

        return list1;
    }

    protected List<Material> materialEntityListToMaterialList(List<MaterialEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Material> list1 = new ArrayList<Material>( list.size() );
        for ( MaterialEntity materialEntity : list ) {
            list1.add( materialMapper.toDomain( materialEntity ) );
        }

        return list1;
    }
}
