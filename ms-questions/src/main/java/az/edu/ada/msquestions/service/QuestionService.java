package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.dto.QuestionCountDTO;
import az.edu.ada.msquestions.model.dto.QuestionDTO;
import az.edu.ada.msquestions.model.entities.Question;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionService {
    List<QuestionDTO> getQuestionsByQuestionPoolId(Long questionPoolId);

    List<QuestionCountDTO> getQuestionCountsByPool();

    Question createQuestion(Question question);
    Optional<Question> getQuestionById(Long id);
    Question updateQuestion(Long id, Question question);
    Question patchQuestion(Long id, Map<String, Object> updates);
    void deleteQuestion(Long id);
    List<Question> getAllQuestions();
}
