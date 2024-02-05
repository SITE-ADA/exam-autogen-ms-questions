package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.QuestionPool;
import az.edu.ada.msquestions.model.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionPoolRepository extends JpaRepository<QuestionPool, Long> {
}
