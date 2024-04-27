package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import az.edu.ada.msquestions.model.request.CorrectAnswerRequest;
import az.edu.ada.msquestions.model.request.CorrectAnswersRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CorrectAnswerService {
    List<CorrectAnswer> createCorrectAnswer(CorrectAnswersRequest correctAnswerRequest);
    Optional<CorrectAnswer> getCorrectAnswerById(Long id);
    CorrectAnswer updateCorrectAnswer(Long id, CorrectAnswer correctAnswer);
    CorrectAnswer patchCorrectAnswer(Long id, Map<String, Object> updates);
    void deleteCorrectAnswer(Long id);
    List<CorrectAnswer> getAllCorrectAnswers();
}
