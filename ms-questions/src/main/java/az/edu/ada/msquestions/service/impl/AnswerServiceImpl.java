package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.Answer;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.request.AnswerRequest;
import az.edu.ada.msquestions.repository.AnswerRepository;
import az.edu.ada.msquestions.repository.QuestionRepository;
import az.edu.ada.msquestions.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Answer> createAnswers(AnswerRequest answerRequest) {

        Question question = questionRepository.findById(answerRequest.getQuestion())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        List<Answer> answers = answerRequest.getText().stream()
                .map(text -> Answer.builder()
                        .question(question)
                        .text(text)
                        .build())
                .collect(Collectors.toList());

        // Save all answers to the database
        return answerRepository.saveAll(answers);
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
