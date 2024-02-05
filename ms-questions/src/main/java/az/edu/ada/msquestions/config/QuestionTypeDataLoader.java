package az.edu.ada.msquestions.config;

import az.edu.ada.msquestions.model.entities.QuestionType;
import az.edu.ada.msquestions.model.enums.EQuestionType;
import az.edu.ada.msquestions.repository.QuestionTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QuestionTypeDataLoader implements CommandLineRunner {
    private final QuestionTypeRepository questionTypeRepository;

    public QuestionTypeDataLoader(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public void run(String... args) {
        for (EQuestionType eQuestionType : EQuestionType.values()) {
            QuestionType questionType = questionTypeRepository.findByQuestionType(eQuestionType);
            if (questionType == null) {
                questionType = QuestionType.builder()
                        .questionType(eQuestionType)
                        .build();
                questionTypeRepository.save(questionType);
            }
        }
    }
}
