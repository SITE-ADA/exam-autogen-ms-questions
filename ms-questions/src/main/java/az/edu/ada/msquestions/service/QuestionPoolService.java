package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.QuestionPool;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionPoolService {
    QuestionPool createQuestionPool(QuestionPool questionPool);

    List<QuestionPool> getAllQuestionPools();

    Optional<QuestionPool> getQuestionPoolById(Long id);

    QuestionPool updateQuestionPool(Long id, QuestionPool updatedQuestionPool);

    @Transactional
    QuestionPool patchQuestionPool(Long id, Map<String, Object> updates);

    void deleteQuestionPool(Long id);

}
