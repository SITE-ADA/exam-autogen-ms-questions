package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.Answer;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.request.AnswerRequest;
import az.edu.ada.msquestions.model.request.AnswersRequest;
import az.edu.ada.msquestions.repository.AnswerRepository;
import az.edu.ada.msquestions.repository.QuestionRepository;
import az.edu.ada.msquestions.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
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
    public List<Answer> createAnswers(AnswersRequest answersRequests) {
        List<Answer> answers = new ArrayList<>();

        for(AnswerRequest answerRequest: answersRequests.getAnswerRequests()){

            Question question = questionRepository.findById(answerRequest.getQuestion())
                    .orElseThrow(() -> new IllegalArgumentException("Question not found"));

            if (answerRequest.getText().size() != answerRequest.getAnswerOptions().size()) {
                throw new IllegalArgumentException("The number of texts must match the number of answer options.");
            }

            Iterator<String> textIterator = answerRequest.getText().iterator();
            Iterator<String> optionsIterator = answerRequest.getAnswerOptions().iterator();

            while (textIterator.hasNext() && optionsIterator.hasNext()) {
                String text = textIterator.next();
                String answerOption = optionsIterator.next();

                Answer answer = Answer.builder()
                        .question(question)
                        .text(text)
                        .answerOption(answerOption)
                        .build();

                answers.add(answer);
            }
        }

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
