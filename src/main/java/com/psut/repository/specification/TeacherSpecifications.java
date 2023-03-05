package com.psut.repository.specification;

import com.psut.repository.entity.TeacherEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(params = "firstName", path = "firstName", spec = Like.class),
        @Spec(params = "lastName", path = "lastName", spec = Like.class),
        @Spec(params = "gender", path = "gender", spec = Equal.class),
        @Spec(params = "status", path = "status", spec = Equal.class),
        @Spec(params = "city", path = "city", spec = Like.class),
        @Spec(params = "targetedStudents", path = "targetedStudents", spec = In.class),
        @Spec(params = "teachingWay", path = "teachingWay", spec = In.class),
        @Spec(params = "rateAvg", path = "rateAvg", spec = GreaterThanOrEqual.class)
})
public interface TeacherSpecifications extends Specification<TeacherEntity> {
}
