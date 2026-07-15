package co.springcore.learning.repository;

import co.springcore.learning.dto.response.InstructorProfileResponse;
import co.springcore.learning.entity.InstructorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionProfileRepository extends JpaRepository<InstructorProfileEntity, Long> {
}
