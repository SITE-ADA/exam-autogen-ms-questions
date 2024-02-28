package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
