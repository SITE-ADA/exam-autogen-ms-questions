package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import az.edu.ada.msquestions.model.request.CorrectAnswerRequest;
import az.edu.ada.msquestions.repository.AnswerRepository;
import az.edu.ada.msquestions.repository.CorrectAnswerRepository;
import az.edu.ada.msquestions.repository.QuestionRepository;
import az.edu.ada.msquestions.service.CorrectAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CorrectAnswerServiceImpl implements CorrectAnswerService {

    private final CorrectAnswerRepository correctAnswerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public CorrectAnswerServiceImpl(CorrectAnswerRepository correctAnswerRepository,
                                    QuestionRepository questionRepository,
                                    AnswerRepository answerRepository) {
        this.correctAnswerRepository = correctAnswerRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public CorrectAnswer createCorrectAnswer(CorrectAnswerRequest correctAnswerRequest) {

        var correctAnswer = CorrectAnswer.builder()
                .question(questionRepository.findById(correctAnswerRequest.getQuestion()).get())
                .answer(answerRepository.findById(correctAnswerRequest.getAnswer()).get())
                .build();

        return correctAnswerRepository.save(correctAnswer);
    }

    @Override
    public Optional<CorrectAnswer> getCorrectAnswerById(Long id) {
        return correctAnswerRepository.findById(id);
    }

    @Override
    public CorrectAnswer updateCorrectAnswer(Long id, CorrectAnswer updatedCorrectAnswer) {
        Optional<CorrectAnswer> existingCorrectAnswerOptional = correctAnswerRepository.findById(id);

        if (existingCorrectAnswerOptional.isPresent()) {
            updatedCorrectAnswer.setId(id);
            return correctAnswerRepository.save(updatedCorrectAnswer);
        } else {
            return null;
        }
    }

    @Override
    public CorrectAnswer patchCorrectAnswer(Long id, Map<String, Object> updates) {
        Optional<CorrectAnswer> optionalCorrectAnswer = correctAnswerRepository.findById(id);
        if (!optionalCorrectAnswer.isPresent()) {
            return null;
        }

        CorrectAnswer correctAnswer = optionalCorrectAnswer.get();
        applyPatchToCorrectAnswer(correctAnswer, updates);
        correctAnswerRepository.save(correctAnswer);
        return correctAnswer;
    }

    private void applyPatchToCorrectAnswer(CorrectAnswer correctAnswer, Map<String, Object> updates) {
        Class<?> clazz = correctAnswer.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(correctAnswer, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteCorrectAnswer(Long id) {
        correctAnswerRepository.deleteById(id);
    }

    @Override
    public List<CorrectAnswer> getAllCorrectAnswers() {
        return correctAnswerRepository.findAll();
    }
}
