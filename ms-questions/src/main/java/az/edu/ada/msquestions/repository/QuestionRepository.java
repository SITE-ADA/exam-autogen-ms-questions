package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.entities.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @EntityGraph(attributePaths = {"questionType", "tags"})
    List<Question> findByQuestionPoolId(Long questionPoolId);

    @Query("SELECT q.questionPool.id, COUNT(q) FROM Question q GROUP BY q.questionPool.id")
    List<Object[]> countQuestionsByQuestionPoolId();
}
