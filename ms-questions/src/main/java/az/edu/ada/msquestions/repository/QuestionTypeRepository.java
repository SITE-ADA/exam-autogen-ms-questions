package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.QuestionType;
import az.edu.ada.msquestions.model.enums.EQuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    QuestionType findByQuestionType(EQuestionType questionType);

}
