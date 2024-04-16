package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import az.edu.ada.msquestions.model.request.CorrectAnswerRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CorrectAnswerService {
    CorrectAnswer createCorrectAnswer(CorrectAnswerRequest correctAnswerRequest);
    Optional<CorrectAnswer> getCorrectAnswerById(Long id);
    CorrectAnswer updateCorrectAnswer(Long id, CorrectAnswer correctAnswer);
    CorrectAnswer patchCorrectAnswer(Long id, Map<String, Object> updates);
    void deleteCorrectAnswer(Long id);
    List<CorrectAnswer> getAllCorrectAnswers();
}
