package co.springcore.learning.repository;

import co.springcore.learning.dto.response.EnrollmentResponse;
import co.springcore.learning.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Integer> {
}
