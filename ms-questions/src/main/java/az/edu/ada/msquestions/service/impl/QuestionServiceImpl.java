package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.dto.QuestionCountDTO;
import az.edu.ada.msquestions.model.dto.QuestionDTO;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.entities.Tag;
import az.edu.ada.msquestions.repository.QuestionRepository;
import az.edu.ada.msquestions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuestionPoolId(Long questionPoolId) {
        List<Question> questions = questionRepository.findByQuestionPoolId(questionPoolId);
        System.out.println(questions);
        return questions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuestionCountDTO> getQuestionCountsByPool() {
        return questionRepository.countQuestionsByQuestionPoolId().stream()
                .map(result -> new QuestionCountDTO((Long) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setQuestionTypeName(question.getQuestionType().getQuestionType().name());
        dto.setTags(question.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        // Map other fields as needed
        return dto;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Optional<Question> existingQuestionOptional = questionRepository.findById(id);

        if (existingQuestionOptional.isPresent()) {
            updatedQuestion.setId(id);
            return questionRepository.save(updatedQuestion);
        } else {
            return null;
        }
    }

    @Override
    public Question patchQuestion(Long id, Map<String, Object> updates) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            return null;
        }

        Question question = optionalQuestion.get();
        applyPatchToQuestion(question, updates);
        questionRepository.save(question);
        return question;
    }

    private void applyPatchToQuestion(Question question, Map<String, Object> updates) {
        Class<?> clazz = question.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(question, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
