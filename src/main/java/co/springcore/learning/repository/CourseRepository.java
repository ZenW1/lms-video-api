package co.springcore.learning.repository;

import co.springcore.learning.dto.response.CourseResponse;
import co.springcore.learning.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer>{

}

