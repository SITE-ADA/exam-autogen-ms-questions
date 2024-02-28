package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.Answer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnswerService {
    Answer createAnswer(Answer answer);
    Optional<Answer> getAnswerById(Long id);
    Answer updateAnswer(Long id, Answer answer);
    Answer patchAnswer(Long id, Map<String, Object> updates);
    void deleteAnswer(Long id);
    List<Answer> getAllAnswers();
}
