package co.springcore.learning.repository;

import co.springcore.learning.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<CommentEntity, Integer> {

}