package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.QuestionType;
import az.edu.ada.msquestions.repository.QuestionTypeRepository;
import az.edu.ada.msquestions.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;

    @Autowired
    public QuestionTypeServiceImpl(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public QuestionType createQuestionType(QuestionType questionType) {
        return questionTypeRepository.save(questionType);
    }

    @Override
    public List<QuestionType> getAllQuestionTypes() {
        return questionTypeRepository.findAll();
    }

    @Override
    public Optional<QuestionType> getQuestionTypeById(Long id) {
        return questionTypeRepository.findById(id);
    }

    @Override
    public QuestionType updateQuestionType(Long id, QuestionType updatedQuestionType) {
        Optional<QuestionType> existingQuestionTypeOptional = questionTypeRepository.findById(id);

        if (existingQuestionTypeOptional.isPresent()) {
            updatedQuestionType.setId(id);
            return questionTypeRepository.save(updatedQuestionType);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public QuestionType patchQuestionType(Long id, Map<String, Object> updates) {
        Optional<QuestionType> optionalQuestionType = questionTypeRepository.findById(id);
        if (optionalQuestionType.isEmpty()) {
            return null;
        }

        QuestionType questionType = optionalQuestionType.get();
        applyPatchToQuestionType(questionType, updates);
        questionTypeRepository.save(questionType);
        return questionType;
    }

    private void applyPatchToQuestionType(QuestionType questionType, Map<String, Object> updates) {
        Class<?> clazz = questionType.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(questionType, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    public void deleteQuestionType(Long id) {
        questionTypeRepository.deleteById(id);
    }
}
