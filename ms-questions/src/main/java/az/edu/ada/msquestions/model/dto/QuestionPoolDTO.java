package az.edu.ada.msquestions.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionPoolDTO {
    private Long id;
    private String name;
    private String description;
    private SubjectDTO subject;
    private Long userId;
    private Integer numOfQuestions;
}
