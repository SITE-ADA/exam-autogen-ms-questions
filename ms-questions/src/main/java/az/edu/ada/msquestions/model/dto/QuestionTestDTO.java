package az.edu.ada.msquestions.model.dto;

import az.edu.ada.msquestions.model.entities.Answer;
import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionTestDTO {
    private Long questionId;
    private String text;
    private String notes;
    private Double defaultScore;
    private Long questionTypeId;
    private Long questionPoolId;
    private Set<Long> tagsIds;
    private List<AnswerDTO> answers;
    private CorrectAnswerDTO correctAnswer;
}
