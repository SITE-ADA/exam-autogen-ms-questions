package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Answer;
import az.edu.ada.msquestions.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Set<Answer> findAllByIdIn(Set<Long> ids);
}
