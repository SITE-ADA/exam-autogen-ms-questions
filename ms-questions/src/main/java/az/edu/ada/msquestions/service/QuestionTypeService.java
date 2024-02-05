package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.QuestionType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionTypeService {
    QuestionType createQuestionType(QuestionType questionType);
    Optional<QuestionType> getQuestionTypeById(Long id);
    QuestionType updateQuestionType(Long id, QuestionType questionType);
    QuestionType patchQuestionType(Long id, Map<String, Object> updates);
    void deleteQuestionType(Long id);
    List<QuestionType> getAllQuestionTypes();
}
