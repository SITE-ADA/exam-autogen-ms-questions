package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.Answer;
import az.edu.ada.msquestions.repository.AnswerRepository;
import az.edu.ada.msquestions.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer updateAnswer(Long id, Answer updatedAnswer) {
        Optional<Answer> existingAnswerOptional = answerRepository.findById(id);

        if (existingAnswerOptional.isPresent()) {
            updatedAnswer.setId(id);
                return answerRepository.save(updatedAnswer);
        } else {
            return null;
        }
    }

    @Override
    public Answer patchAnswer(Long id, Map<String, Object> updates) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()) {
            return null;
        }

        Answer answer = optionalAnswer.get();
        applyPatchToAnswer(answer, updates);
        answerRepository.save(answer);
        return answer;
    }

    private void applyPatchToAnswer(Answer answer, Map<String, Object> updates) {
        Class<?> clazz = answer.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(answer, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }
}
