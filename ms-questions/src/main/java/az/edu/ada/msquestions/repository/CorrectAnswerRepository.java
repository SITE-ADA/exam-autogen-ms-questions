package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
    CorrectAnswer findByQuestionId(Long id);
}
