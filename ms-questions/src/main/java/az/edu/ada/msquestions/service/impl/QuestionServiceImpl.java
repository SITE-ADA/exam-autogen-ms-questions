package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.dto.*;
import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.entities.Tag;
import az.edu.ada.msquestions.model.request.QuestionRequest;
import az.edu.ada.msquestions.repository.*;
import az.edu.ada.msquestions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionPoolRepository questionPoolRepository;
    private final QuestionTypeRepository questionTypeRepository;
    private final AnswerRepository answerRepository;
    private final CorrectAnswerRepository correctAnswerRepository;
    private final TagRepository tagRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionPoolRepository questionPoolRepository,
                               QuestionTypeRepository questionTypeRepository, TagRepository tagRepository,
                               AnswerRepository answerRepository, CorrectAnswerRepository correctAnswerRepository) {
        this.questionRepository = questionRepository;
        this.questionPoolRepository = questionPoolRepository;
        this.questionTypeRepository = questionTypeRepository;
        this.answerRepository = answerRepository;
        this.correctAnswerRepository = correctAnswerRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public QuestionTestDTO getQuestionByIdForTest(Long questionId) {

        Question question = questionRepository.findById(questionId).get();

        Set<Long> tagsIds = question.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());

        List<AnswerDTO> answerDTOs = answerRepository.findAllByQuestionId(question.getId()).stream()
                .map(answer -> AnswerDTO.builder()
                        .id(answer.getId())
                        .text(answer.getText())
                        .answerOption(answer.getAnswerOption())
                        .build())
                .collect(Collectors.toList());

        CorrectAnswer correctAnswer = correctAnswerRepository.findByQuestionId(question.getId());
        CorrectAnswerDTO correctAnswerDTO = CorrectAnswerDTO.builder()
                .id(correctAnswer.getId())
                .answer(AnswerDTO.builder()
                        .id(correctAnswer.getAnswer().getId())
                        .text(correctAnswer.getAnswer().getText())
                        .build())
                .build();

        return QuestionTestDTO.builder()
                .questionId(question.getId())
                .text(question.getText())
                .notes(question.getNotes())
                .defaultScore(question.getDefaultScore())
                .questionTypeId(question.getQuestionType().getId())
                .questionPoolId(question.getQuestionPool().getId())
                .tagsIds(tagsIds)
                .answers(answerDTOs)
                .correctAnswer(correctAnswerDTO)
                .build();
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
    public Question createQuestion(QuestionRequest questionRequest) {

        Set<Long> tagsIds = questionRequest.getTagsIds();
        Set<Tag> tags = tagRepository.findAllByIdIn(tagsIds);

        var question = Question.builder()
                .text(questionRequest.getText())
                .notes(questionRequest.getNotes())
                .defaultScore(questionRequest.getDefaultScore())
                .questionType(questionTypeRepository.findById(questionRequest.getQuestionTypeId()).get())
                .questionPool(questionPoolRepository.findById(questionRequest.getQuestionPoolId()).get())
                .tags(tags)
                .build();

        for(Tag tag: tags) {
            tag.getQuestions().add(question);
            tag.setQuestions(tag.getQuestions());
            tagRepository.save(tag);
        }

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
