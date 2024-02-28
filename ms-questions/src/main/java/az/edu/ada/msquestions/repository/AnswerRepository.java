package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
