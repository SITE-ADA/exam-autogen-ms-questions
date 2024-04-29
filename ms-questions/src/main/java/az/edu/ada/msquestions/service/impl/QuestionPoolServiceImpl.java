package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.dto.QuestionDTO;
import az.edu.ada.msquestions.model.dto.QuestionPoolDTO;
import az.edu.ada.msquestions.model.dto.SubjectDTO;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.entities.QuestionPool;
import az.edu.ada.msquestions.repository.QuestionPoolRepository;
import az.edu.ada.msquestions.repository.QuestionRepository;
import az.edu.ada.msquestions.repository.SubjectRepository;
import az.edu.ada.msquestions.service.QuestionPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionPoolServiceImpl implements QuestionPoolService {

    private final QuestionPoolRepository questionPoolRepository;
    private final QuestionRepository questionRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public QuestionPoolServiceImpl(QuestionPoolRepository questionPoolRepository,
                                   QuestionRepository questionRepository,
                                   SubjectRepository subjectRepository) {
        this.questionPoolRepository = questionPoolRepository;
        this.questionRepository = questionRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public QuestionPool createQuestionPool(QuestionPool questionPool) {
        return questionPoolRepository.save(questionPool);
    }

    @Override
    public List<QuestionPoolDTO> getAllQuestionPools() {
        List<QuestionPool> questionPools = questionPoolRepository.findAll();
        List<QuestionPoolDTO> questionPoolsDTO = new ArrayList<>();
        for(QuestionPool questionPool: questionPools){
            var questions = questionRepository.findByQuestionPoolId(questionPool.getId());

            var subject = subjectRepository.findById(questionPool.getSubjectId()).get();
            var subjectDTO = SubjectDTO.builder()
                    .id(subject.getId())
                    .name(subject.getName())
                    .description(subject.getDescription())
                    .courseObjectives(subject.getCourseObjectives())
                    .status(subject.getSubjectStatusId())
                    .crn(subject.getCrn())
                    .term(subject.getTerm())
                    .build();

            var questionPoolDTO = QuestionPoolDTO.builder()
                    .id(questionPool.getId())
                    .name(questionPool.getName())
                    .description(questionPool.getDescription())
                    .subject(subjectDTO)
                    .userId(questionPool.getUserId())
                    .numOfQuestions(questions.size())
                    .build();

            questionPoolsDTO.add(questionPoolDTO);
        }

        return questionPoolsDTO;
    }

    @Override
    public Optional<QuestionPool> getQuestionPoolById(Long id) {
        return questionPoolRepository.findById(id);
    }

    @Override
    public QuestionPool updateQuestionPool(Long id, QuestionPool updatedQuestionPool) {
        Optional<QuestionPool> existingQuestionPoolOptional = questionPoolRepository.findById(id);

        if (existingQuestionPoolOptional.isPresent()) {
            updatedQuestionPool.setId(id);
            return questionPoolRepository.save(updatedQuestionPool);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public QuestionPool patchQuestionPool(Long id, Map<String, Object> updates) {
        Optional<QuestionPool> optionalQuestionPool = questionPoolRepository.findById(id);
        if (!optionalQuestionPool.isPresent()) {
            return null;
        }

        QuestionPool questionPool = optionalQuestionPool.get();
        applyPatchToQuestionPool(questionPool, updates);
        questionPoolRepository.save(questionPool);
        return questionPool;
    }

    private void applyPatchToQuestionPool(QuestionPool questionPool, Map<String, Object> updates) {
        Class<?> clazz = questionPool.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                var val = value;
                if(key.equals("userId") || key.equals("subjectId")) {
                    val = Long.parseLong(value.toString());
                }
                field.set(questionPool, val);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteQuestionPool(Long id) {
        questionPoolRepository.deleteById(id);
    }
}
