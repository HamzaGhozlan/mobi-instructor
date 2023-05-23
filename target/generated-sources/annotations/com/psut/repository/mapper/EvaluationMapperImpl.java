package com.psut.repository.mapper;

import com.psut.model.evaluation.Evaluation;
import com.psut.model.evaluation.Evaluation.EvaluationBuilder;
import com.psut.repository.entity.EvaluationEntity;
import com.psut.repository.entity.StudentEntity;
import com.psut.repository.entity.TeacherEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-23T12:51:34+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class EvaluationMapperImpl implements EvaluationMapper {

    @Override
    public EvaluationEntity toEntity(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        EvaluationEntity evaluationEntity = new EvaluationEntity();

        evaluationEntity.setTeacher( evaluationToTeacherEntity( evaluation ) );
        evaluationEntity.setStudent( evaluationToStudentEntity( evaluation ) );
        evaluationEntity.setId( evaluation.getId() );
        evaluationEntity.setRate( evaluation.getRate() );
        evaluationEntity.setReview( evaluation.getReview() );

        return evaluationEntity;
    }

    @Override
    public Evaluation toDomain(EvaluationEntity evaluationEntity) {
        if ( evaluationEntity == null ) {
            return null;
        }

        EvaluationBuilder evaluation = Evaluation.builder();

        evaluation.teacherId( evaluationEntityTeacherId( evaluationEntity ) );
        evaluation.studentId( evaluationEntityStudentId( evaluationEntity ) );
        evaluation.id( evaluationEntity.getId() );
        evaluation.rate( evaluationEntity.getRate() );
        evaluation.review( evaluationEntity.getReview() );

        return evaluation.build();
    }

    protected TeacherEntity evaluationToTeacherEntity(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        TeacherEntity teacherEntity = new TeacherEntity();

        teacherEntity.setId( evaluation.getTeacherId() );

        return teacherEntity;
    }

    protected StudentEntity evaluationToStudentEntity(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( evaluation.getStudentId() );

        return studentEntity;
    }

    private Long evaluationEntityTeacherId(EvaluationEntity evaluationEntity) {
        if ( evaluationEntity == null ) {
            return null;
        }
        TeacherEntity teacher = evaluationEntity.getTeacher();
        if ( teacher == null ) {
            return null;
        }
        Long id = teacher.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long evaluationEntityStudentId(EvaluationEntity evaluationEntity) {
        if ( evaluationEntity == null ) {
            return null;
        }
        StudentEntity student = evaluationEntity.getStudent();
        if ( student == null ) {
            return null;
        }
        Long id = student.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
