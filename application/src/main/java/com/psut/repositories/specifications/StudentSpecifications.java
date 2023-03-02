package com.psut.repositories.specifications;

import com.psut.repositories.entities.StudentEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(params = "firstName", path = "firstName", spec = Like.class),
        @Spec(params = "lastName", path = "lastName", spec = Like.class),
        @Spec(params = "gender", path = "gender", spec = Equal.class),
        @Spec(params = "status", path = "status", spec = Equal.class)
})
public interface StudentSpecifications extends Specification<StudentEntity> {
}
