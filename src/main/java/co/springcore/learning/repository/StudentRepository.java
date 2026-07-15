package co.springcore.learning.repository;

import co.springcore.learning.entity.StudentProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentProfileEntity, Long> {
}
